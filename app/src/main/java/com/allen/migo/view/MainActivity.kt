package com.allen.migo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.allen.migo.viewmodel.StatusViewModel
import com.allen.migo.R
import com.allen.migo.network.core.NetworkHandle
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val statusViewModel: StatusViewModel by viewModel()
    private val networkHandle: NetworkHandle by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initObserver()
    }

    private fun initView() {
        btn_status.setOnClickListener {
            statusViewModel.getNetworkStatus()
        }
    }

    private fun initObserver() {
        statusViewModel.message.observe(this, Observer {
            text_message.text = getString(R.string.text_message, it)
        })

        statusViewModel.status.observe(this, Observer {
            text_status.text = getString(R.string.text_status, it.toString())
        })

        networkHandle.apiEnv.observe(this, Observer {
            text_host.text = "${it.name} : ${it.value}"
        })
    }
}