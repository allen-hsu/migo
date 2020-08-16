package com.allen.migo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.framework.BaseFragment
import com.allen.migo.logic.PassParcelize
import com.allen.migo.logic.PassType
import com.allen.migo.ui.PassListAdapter
import com.allen.migo.viewmodel.PassListViewModel
import kotlinx.android.synthetic.main.fragment_pass_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class PassListFragment(private val passType: PassType) : BaseFragment() {
    private val viewModel: PassListViewModel by viewModel()
    private val adapter: PassListAdapter =
        PassListAdapter(
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
            }
        )

    override fun getLayout(): Int {
        return R.layout.fragment_pass_list
    }

    override fun init(view: View) {
        setupAdapter(view)
        initObserver(view)
    }

    override fun release(view: View) {
        view.recycler_view_pass.adapter = null
    }

    private fun initObserver(view: View) {
        viewModel.queryPassList().observe(this, Observer {
            adapter.run {
                setItems(it.filter {
                    it.passType().compareTo(passType) == 0
                })
                notifyDataSetChanged()
            }
        })
    }

    private fun setupAdapter(view: View) {
        view.recycler_view_pass.adapter = adapter
    }

}