package com.allen.migo.network.core

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MockWifiNetworkHandleProvider: NetworkHandle {
    private val _networkAvailable = MutableLiveData<Boolean>(true)

    private val _networkMode = MutableLiveData<NetworkMode>(
        NetworkMode.WIFI
    )

    private val _apiEnv = MutableLiveData<ApiEnv>(
        ApiEnv.PUBLIC
    )

    init {
        if(_networkMode.value == NetworkMode.WIFI) {
            _apiEnv.postValue(ApiEnv.PRIVATE)
        } else {
            _apiEnv.postValue(ApiEnv.PUBLIC)
        }
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

class MockCellularProvider: NetworkHandle {
    private val _networkAvailable = MutableLiveData<Boolean>(true)

    private val _networkMode = MutableLiveData<NetworkMode>(
        NetworkMode.CELLULAR
    )

    private val _apiEnv = MutableLiveData<ApiEnv>(
        ApiEnv.PUBLIC
    )

    init {
        if(_networkMode.value == NetworkMode.WIFI) {
            _apiEnv.postValue(ApiEnv.PRIVATE)
        } else {
            _apiEnv.postValue(ApiEnv.PUBLIC)
        }
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

class MockNoneNetworkProvider: NetworkHandle {
    private val _networkAvailable = MutableLiveData<Boolean>(false)

    private val _networkMode = MutableLiveData<NetworkMode>(
        NetworkMode.NONE
    )

    private val _apiEnv = MutableLiveData<ApiEnv>(
        ApiEnv.PUBLIC
    )

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