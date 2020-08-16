package com.allen.migo.framework

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseActivity : AppCompatActivity() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    abstract fun getFragmentNavigate(): FragmentNavigate?

    open fun showLoading() {
        _isLoading.postValue(true)
    }

    open fun hideLoading() {
        _isLoading.postValue(false)
    }

    override fun onBackPressed() {
        getFragmentNavigate()?.let {
            if (!it.popBackStack()) {
                finish()
            }
        } ?: super.onBackPressed()
    }
}