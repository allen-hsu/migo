package com.allen.migo.network

import android.content.Context
import android.net.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.telephony.TelephonyManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NetworkManager private constructor(context: Context) {
    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback

    private val _networkAvailable = MutableLiveData<Boolean>(false)
    val networkAvailable: LiveData<Boolean> get() = _networkAvailable

    private val _networkMode = MutableLiveData<NetworkMode>(NetworkMode.NONE)
    val networkMode: LiveData<NetworkMode> get() = _networkMode

    init {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _networkAvailable.postValue(true)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                _networkAvailable.postValue(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _networkAvailable.postValue(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                _networkAvailable.postValue(false)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                var connected = false
                _networkAvailable.value?.run {
                    connected = this
                }

                if(!connected) {
                    _networkMode.postValue(NetworkMode.NONE)
                    return
                }

                when {
                    networkCapabilities.hasTransport(TRANSPORT_WIFI) -> _networkMode.postValue(NetworkMode.WIFI)
                    networkCapabilities.hasTransport(TRANSPORT_CELLULAR) -> _networkMode.postValue(NetworkMode.CELLULAR)
                    else -> {
                        _networkMode.postValue(NetworkMode.OTHER)
                    }
                }

            }
        }
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        //ref: https://stackoverflow.com/questions/32185628/connectivitymanager-requestnetwork-in-android-6-0
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) {
            connectivityManager?.requestNetwork(
                NetworkRequest.Builder().build(),
                networkCallback
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NetworkManager? = null
        private var TAG = NetworkManager::class.java.simpleName

        fun init(context: Context) {
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: NetworkManager(context).also { INSTANCE = it }
            }
        }

        fun getInstance(): NetworkManager {
            return INSTANCE ?: throw Exception("Must call init before getInstance.")
        }
    }
}