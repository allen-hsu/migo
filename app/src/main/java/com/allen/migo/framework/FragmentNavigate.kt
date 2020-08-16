package com.allen.migo.framework

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentNavigate(
    @IdRes private val layoutId: Int,
    private val fragmentManager: FragmentManager,
    private val changeFragmentHandle: (() -> Unit)? = null
) {


    private fun getCurrentFragment(): Fragment? {
        return fragmentManager.findFragmentById(layoutId)
    }

    fun changeFragment(fragment: Fragment): Boolean {
        return replaceFragment(fragment, true)
    }

    fun pushFragment(fragment: Fragment): Boolean {
        return replaceFragment(fragment, false)
    }

    fun popBackStack(): Boolean {
        val currentFragment = getCurrentFragment()
        if (fragmentManager.backStackEntryCount > 1 && !(currentFragment as BaseFragment).onBackPressed()) {
            fragmentManager.popBackStack()
            return true
        }
        return false
    }

    private fun replaceFragment(fragment: Fragment, cleanStack: Boolean = false): Boolean {
        if (fragment === getCurrentFragment()) {
            return false
        }

        if (cleanStack) {
            clearBackStack()
        }

        fragmentManager.beginTransaction().apply {
            replace(layoutId, fragment)
            addToBackStack(null)
        }.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
        changeFragmentHandle?.invoke()
        return true
    }

    private fun clearBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            val first = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
