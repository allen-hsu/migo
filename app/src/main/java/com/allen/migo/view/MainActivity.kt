package com.allen.migo.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.fragment.StatusFragment
import com.allen.migo.fragment.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_network -> changeFragment(StatusFragment())
                R.id.navigation_wallet -> changeFragment(WalletFragment())
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initObserver()
    }

    private fun initView() {
        changeFragment(StatusFragment())
        nav_view.setOnNavigationItemSelectedListener(navigationListener)
    }

    private fun initObserver() {
        isLoading.observe(this, Observer {
            if(it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }
}