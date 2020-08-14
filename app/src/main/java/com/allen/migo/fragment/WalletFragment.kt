package com.allen.migo.fragment

import android.view.View
import com.allen.migo.R
import com.allen.migo.logic.PassType
import com.allen.migo.viewpager.WalletViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wallet.view.*

class WalletFragment : BaseFragment() {

    override fun getLayout() : Int {
        return R.layout.fragment_wallet
    }

    override fun init(view: View) {

        view.pager.adapter = WalletViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )
        TabLayoutMediator(view.tab_layout, view.pager) { tab, position ->
            tab.text = PassType.values()[position].name
        }.attach()
    }
}