package com.allen.migo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.viewmodel.StatusViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_status.*
import kotlinx.android.synthetic.main.fragment_status.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class StatusFragment : Fragment() {
    private val statusViewModel: StatusViewModel by viewModel()
    private val networkHandle: NetworkHandle by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_status, container, false)
        initObserver(root)
        initView(root)
        return root
    }

    private fun initObserver(view: View) {
        statusViewModel.message.observe(this.viewLifecycleOwner, Observer {
            view.text_message.text = getString(R.string.text_message, it)
        })

        statusViewModel.status.observe(this.viewLifecycleOwner, Observer {
            view.text_status.text = getString(R.string.text_status, it.toString())
        })

        networkHandle.apiEnv.observe(this.viewLifecycleOwner, Observer {
            view.text_host.text = "${it.name} : ${it.value}"
        })
    }

    private fun initView(view: View) {
        view.btn_status.setOnClickListener {
            statusViewModel.getNetworkStatus()
        }
    }
}