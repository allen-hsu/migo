package com.allen.migo.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.allen.migo.R
import com.allen.migo.ext.hideKeyboard
import com.allen.migo.ext.weakSelf
import com.allen.migo.framework.BaseFragment
import com.allen.migo.logic.PassParcelize
import com.allen.migo.logic.PassType
import com.allen.migo.ui.PassListAdapter
import com.allen.migo.viewmodel.PassListViewModel
import kotlinx.android.synthetic.main.fragment_pass_list.view.*
import kotlinx.android.synthetic.main.fragment_pass_list.view.btn_add_pass
import kotlinx.android.synthetic.main.fragment_pass_list.view.edit_pass_number
import kotlinx.android.synthetic.main.fragment_pass_list.view.spinner
import org.koin.android.viewmodel.ext.android.viewModel


class PassListFragment : BaseFragment() {
    private val viewModel: PassListViewModel by viewModel()
    private lateinit var recyclerViewAdapter: PassListAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_pass_list
    }

    override fun init(view: View) {
        recyclerViewAdapter = PassListAdapter(
            onItemClicked = {
                val fragment = PassDetailFragment.newInstance(PassParcelize(
                    it.unitNum(),
                    it.passStatus(),
                    it.passType(),
                    it.activateDateTime(),
                    it.expiredDateTime(),
                    it.insertDateTime()
                ))
                getBaseActivity().getFragmentNavigate()?.pushFragment(fragment)
            },
            onPassBtnClicked = {
                viewModel.activatePass(it)
            },
            latestExpiredTime = viewModel.getLatestExpiredTime()
        )
        val passTypeArray = PassType.values().filter { it.compareTo(PassType.UNKNOWN) != 0 }
        val spinnerAdapter = ArrayAdapter<PassType>(
            this.requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            passTypeArray
        )
        view.spinner.adapter = spinnerAdapter
        view.recycler_view_pass.adapter = recyclerViewAdapter
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

        initObserver(view)
    }

    override fun release(view: View) {
        view.recycler_view_pass.adapter = null
        view.spinner.adapter = null
    }

    private fun initObserver(view: View) {
        viewModel.queryPassList().observe(this, Observer {
            recyclerViewAdapter.run {
                setItems(it)
                notifyDataSetChanged()
            }
        })
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