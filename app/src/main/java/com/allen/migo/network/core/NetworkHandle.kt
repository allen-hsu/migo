package com.allen.migo.network.core

import android.content.Context
import android.net.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkHandle (private val context: Context) {
    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback

    private val _networkAvailable = MutableLiveData<Boolean>(false)
    val networkAvailable: LiveData<Boolean> get() = _networkAvailable

    private val _networkMode = MutableLiveData<NetworkMode>(
        NetworkMode.NONE
    )
    val networkMode: LiveData<NetworkMode> get() = _networkMode

    private val _apiEnv = MutableLiveData<ApiEnv>(
        ApiEnv.PUBLIC
    )
    val apiEnv: LiveData<ApiEnv> get() = _apiEnv

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
                when {
                    networkCapabilities.hasTransport(TRANSPORT_WIFI) -> _networkMode.postValue(
                        NetworkMode.WIFI
                    )
                    networkCapabilities.hasTransport(TRANSPORT_CELLULAR) -> _networkMode.postValue(
                        NetworkMode.CELLULAR
                    )
                    else -> {
                        _networkMode.postValue(NetworkMode.OTHER)
                    }
                }

                var isConnectWifi = false
                var isConnectCellular = false
                connectivityManager?.run {
                    for(networkInfo in this.allNetworks) {
                        val capabilities = this.getNetworkCapabilities(networkInfo)
                        capabilities?.run {
                            if(this.hasTransport(TRANSPORT_WIFI)) {
                                isConnectWifi = true
                            }
                            if(this.hasTransport(TRANSPORT_CELLULAR)) {
                                isConnectCellular = true
                            }
                        }
                    }
                }

                if(isConnectWifi && isConnectCellular) {
                    _apiEnv.postValue(ApiEnv.PRIVATE)
                } else {
                    _apiEnv.postValue(ApiEnv.PUBLIC)
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
}