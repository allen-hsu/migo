package com.allen.migo.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.framework.BaseActivity
import com.allen.migo.framework.FragmentNavigate
import com.allen.migo.ui.fragment.StatusFragment
import com.allen.migo.ui.fragment.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val fragmentNav: FragmentNavigate by lazy {
        FragmentNavigate(R.id.fragment_container, supportFragmentManager,
            changeFragmentHandle = {
                hideLoading()
                if (supportFragmentManager.backStackEntryCount > 1) {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                } else {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            })
    }

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_network -> getFragmentNavigate()?.changeFragment(StatusFragment())
                R.id.navigation_wallet -> getFragmentNavigate()?.changeFragment(WalletFragment())
            }
            true
        }

    override fun getFragmentNavigate(): FragmentNavigate? {
        return fragmentNav
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        nav_view.setOnNavigationItemSelectedListener(null)
    }

    private fun initView() {
        getFragmentNavigate()?.changeFragment(StatusFragment())
        nav_view.setOnNavigationItemSelectedListener(navigationListener)
        supportFragmentManager.addOnBackStackChangedListener {
            hideLoading()
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    private fun initObserver() {
        isLoading.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}