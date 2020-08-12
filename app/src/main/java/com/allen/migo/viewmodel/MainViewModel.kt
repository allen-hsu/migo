package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allen.migo.network.NetworkManager


class MainViewModel: ViewModel() {

//    private val _isConnectWifi = MutableLiveData<Boolean>()
//    val isConnectWifi: LiveData<Boolean> get() = _isConnectWifi
//
//    private val _isConnectCellular = MutableLiveData<Boolean>()
//    val isConnectCellular: LiveData<Boolean> get() = _isConnectCellular
//
//    private val _isNetworkAvailable = MutableLiveData<Boolean>()
//    val isNetworkAvailable: LiveData<Boolean> get() = _isNetworkAvailable

//    fun test1() {
//        _isConnectWifi.postValue(NetworkManager.getInstance().isConnectedToWifi())
//        _isConnectCellular.postValue(NetworkManager.getInstance().isConnectedToCellular())
//        _isNetworkAvailable.postValue(NetworkManager.getInstance().isNetworkAvailable())
//    }
}
/**
 * Factory for [LiveDataViewModel].
 */
object MainVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel() as T
    }
}