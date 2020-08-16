package com.allen.migo.ui.fragment

import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import com.allen.migo.R
import com.allen.migo.ext.hideKeyboard
import com.allen.migo.ext.weakSelf
import com.allen.migo.framework.BaseFragment
import com.allen.migo.logic.PassType
import com.allen.migo.ui.viewpager.WalletViewPagerAdapter
import com.allen.migo.viewmodel.WalletViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wallet.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class WalletFragment : BaseFragment() {

    private val viewModel: WalletViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_wallet
    }

    override fun init(view: View) {
        view.pager.adapter = WalletViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )
        TabLayoutMediator(view.tab_layout, view.pager) { tab, position ->
            tab.text = PassType.values()[position].name
        }.attach()


        val passTypeArray = PassType.values().filter { it.compareTo(PassType.UNKNOWN) != 0 }
        val adapter = ArrayAdapter<PassType>(
            this.requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            passTypeArray
        )
        view.spinner.adapter = adapter

        view.btn_add_pass.setOnClickListener {
            if (view.edit_pass_number.text.isNullOrEmpty()) {
                return@setOnClickListener
            }

            val textNum = view.edit_pass_number.text.toString()
            if (!checkPassNumValid(textNum)) {
                return@setOnClickListener
            }

            viewModel.addPass(textNum.toInt(), PassType.values()[view.spinner.selectedItemPosition])
            view.edit_pass_number.text.clear()
            view.edit_pass_number.clearFocus()
            hideKeyboard()
        }

        view.edit_pass_number.doOnTextChanged { text, start, before, count ->
            weakSelf().get()?.apply {
                view.btn_add_pass.isEnabled = this.checkPassNumValid(text.toString())
            }
        }
    }

    override fun release(view: View) {
        TabLayoutMediator(view.tab_layout, view.pager) { tab, position ->
            tab.text = PassType.values()[position].name
        }.detach()

        view.pager.adapter = null
    }

    private fun checkPassNumValid(num: String?): Boolean {
        if (num.isNullOrEmpty()) {
            return false
        }

        if (num.toInt() <= 0) {
            return false
        }
        return true;
    }
}