package com.allen.migo.network

interface NetworkStatus {
    fun isNetworkAvailable(): Boolean
    fun isConnectedToWifi() : Boolean
    fun isConnectedToCellular() : Boolean
}