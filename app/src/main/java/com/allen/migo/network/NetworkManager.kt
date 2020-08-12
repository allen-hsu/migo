package com.allen.migo.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

class NetworkManager private constructor(context: Context) :
    NetworkStatus {
    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback

    init {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
            }

            override fun onUnavailable() {
                super.onUnavailable()
            }

            override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                super.onBlockedStatusChanged(network, blocked)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
            }
        }
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) {
            connectivityManager?.requestNetwork(
                NetworkRequest.Builder().build(),
                networkCallback
            )
        }
    }

    override fun isNetworkAvailable(): Boolean {
        return true
    }

    override fun isConnectedToWifi(): Boolean {
        return true
    }

    override fun isConnectedToCellular(): Boolean {
        return true
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