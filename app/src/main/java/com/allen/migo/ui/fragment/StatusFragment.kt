package com.allen.migo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.framework.BaseFragment
import com.allen.migo.network.core.ApiResult
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.viewmodel.StatusViewModel
import kotlinx.android.synthetic.main.fragment_status.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class StatusFragment : BaseFragment() {
    private val statusViewModel: StatusViewModel by viewModel()
    private val networkHandle: NetworkHandle by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_status
    }

    override fun init(view: View) {
        initView(view)
        initObserver(view)
    }

    override fun release(view: View) {

    }

    private fun initObserver(view: View) {
        statusViewModel.message.observe(this.viewLifecycleOwner, Observer {
            view.text_message.text = getString(R.string.text_network_message, it)
        })

        statusViewModel.status.observe(this.viewLifecycleOwner, Observer {
            view.text_status.text = getString(R.string.text_network_status, it.toString())
        })

        networkHandle.apiEnv().observe(this.viewLifecycleOwner, Observer {
            view.text_host.text = "${it.name} : ${it.value}"
        })
        networkHandle.networkAvailable().observe(this.viewLifecycleOwner, Observer {
            view.btn_status.isEnabled = it
            view.text_host.text = getString(R.string.text_status_not_connect)
        })
    }

    private fun initView(view: View) {
        view.btn_status.setOnClickListener {
            statusViewModel.getNetworkStatus().observe(this.viewLifecycleOwner, Observer {
                when (it.status) {
                    ApiResult.Status.SUCCESS -> getBaseActivity().hideLoading()
                    ApiResult.Status.ERROR -> getBaseActivity().hideLoading()
                    ApiResult.Status.LOADING -> getBaseActivity().showLoading()
                }
            })
        }
    }
}