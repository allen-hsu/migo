package com.allen.migo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.allen.migo.R
import com.allen.migo.fragment.StatusFragment
import com.allen.migo.fragment.WalletFragment
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.viewmodel.StatusViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_network -> changeToStatusFragment()
                R.id.navigation_wallet -> changeToWalletFragment()
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        initObserver()
    }

    private fun initView() {
        nav_view.setOnNavigationItemSelectedListener(navigationListener)
//        btn_status.setOnClickListener {
//            statusViewModel.getNetworkStatus()
//        }
    }

    private fun changeToWalletFragment() {
        val manager: FragmentManager = supportFragmentManager
        manager.beginTransaction().replace(R.id.container, WalletFragment()).commit()
    }

    private fun changeToStatusFragment() {
        val manager: FragmentManager = supportFragmentManager
        manager.beginTransaction().replace(R.id.container, StatusFragment()).commit()
    }

}