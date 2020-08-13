package com.allen.migo.data

import com.allen.migo.constant.ApiHostConstant
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.network.core.NetworkMode
import com.allen.migo.network.api.StatusApiResult
import com.allen.migo.network.api.StatusService
import retrofit2.Response

class StatusRemoteDataSource(private val service: StatusService, private val networkHandle: NetworkHandle) {
    suspend fun fetchData() : Response<StatusApiResult> {
        return if(networkHandle.networkMode.value == NetworkMode.WIFI) {
            service.getStatus(ApiHostConstant.PUBLIC_API_HOST)
        } else {
            service.getStatus(ApiHostConstant.PUBLIC_API_HOST)
        }
    }
}