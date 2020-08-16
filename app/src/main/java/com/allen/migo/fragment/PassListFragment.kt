package com.allen.migo.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.allen.migo.PassListAdapter
import com.allen.migo.R
import com.allen.migo.logic.PassType
import com.allen.migo.viewmodel.PassListViewModel
import kotlinx.android.synthetic.main.fragment_pass_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PassListFragment(private val passType: PassType) : BaseFragment() {
    private val viewModel: PassListViewModel by viewModel()
    private val adapter: PassListAdapter = PassListAdapter(
        onItemClicked = {
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