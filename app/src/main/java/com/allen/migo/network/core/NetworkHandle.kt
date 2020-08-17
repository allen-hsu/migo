package com.allen.migo.network.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkHandleProvider(private val context: Context): NetworkHandle {
    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback

    private val _networkAvailable = MutableLiveData<Boolean>(false)

    private val _networkMode = MutableLiveData<NetworkMode>(
        NetworkMode.NONE
    )

    private val _apiEnv = MutableLiveData<ApiEnv>(
        ApiEnv.PUBLIC
    )

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
                    for (networkInfo in this.allNetworks) {
                        val capabilities = this.getNetworkCapabilities(networkInfo)
                        capabilities?.run {
                            if (this.hasTransport(TRANSPORT_WIFI)) {
                                isConnectWifi = true
                            }
                            if (this.hasTransport(TRANSPORT_CELLULAR)) {
                                isConnectCellular = true
                            }
                        }
                    }
                }

                if(isConnectWifi) {
                    _apiEnv.postValue(ApiEnv.PRIVATE)
                } else {
                    _apiEnv.postValue(ApiEnv.PUBLIC)
                }
            }
        }
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        connectivityManager?.requestNetwork(
            NetworkRequest.Builder().build(),
            networkCallback
        )
    }

    override fun networkAvailable(): LiveData<Boolean> {
        return _networkAvailable
    }

    override fun networkMode(): LiveData<NetworkMode> {
        return _networkMode
    }

    override fun apiEnv(): LiveData<ApiEnv> {
        return _apiEnv
    }
}

interface NetworkHandle {
    fun networkAvailable(): LiveData<Boolean>

    fun networkMode(): LiveData<NetworkMode>

    fun apiEnv(): LiveData<ApiEnv>
}