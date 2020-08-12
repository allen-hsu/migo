package com.allen.migo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.allen.migo.viewmodel.MainVMFactory
import com.allen.migo.viewmodel.MainViewModel
import com.allen.migo.R
import com.allen.migo.network.NetworkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkManager.getInstance().networkAvailable.observe(this, Observer {
            network_available.text = it.toString()
        })

        NetworkManager.getInstance().networkMode.observe(this, Observer {
            network_mode.text = it.value
        })
    }
}