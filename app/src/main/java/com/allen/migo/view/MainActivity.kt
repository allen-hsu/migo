package com.allen.migo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.allen.migo.viewmodel.MainVMFactory
import com.allen.migo.viewmodel.MainViewModel
import com.allen.migo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.isConnectCellular.observe(this, Observer {
            connect_cellular.text = it.toString()
        })

        viewModel.isConnectWifi.observe(this, Observer {
            connect_wifi.text = it.toString()
        })

        viewModel.isNetworkAvailable.observe(this, Observer {
            network_available.text = it.toString()
        })
    }
}