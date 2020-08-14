package com.allen.migo.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.allen.migo.fragment.PassListFragment
import com.allen.migo.logic.PassType

class WalletViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return PassType.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return PassListFragment()
    }
}