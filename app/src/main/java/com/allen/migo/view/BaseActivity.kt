package com.allen.migo.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allen.migo.R

abstract class BaseActivity : AppCompatActivity() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    open fun showLoading() {
        _isLoading.postValue(true)
    }

    open fun hideLoading() {
        _isLoading.postValue(false)
    }

    protected fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        hideLoading()
        if(cleanStack) {
            clearBackStack()
        }

        supportFragmentManager.beginTransaction().apply {
            this.replace(R.id.fragment_container, f)
            this.addToBackStack(null)
            this.commit()
        }
    }

    private fun clearBackStack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}