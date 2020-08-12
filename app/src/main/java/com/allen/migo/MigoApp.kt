package com.allen.migo

import androidx.multidex.MultiDexApplication
import com.allen.migo.network.NetworkManager


class MigoApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        NetworkManager.init(this)
    }
}