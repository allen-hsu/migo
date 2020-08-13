package com.allen.migo.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.fragment.StatusFragment
import com.allen.migo.fragment.WalletFragment
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.viewmodel.StatusViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

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