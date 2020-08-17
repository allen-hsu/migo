package com.allen.migo.data

import com.allen.migo.constant.ApiHostConstant
import com.allen.migo.network.api.StatusApiResult
import com.allen.migo.network.api.StatusService
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.network.core.NetworkMode
import retrofit2.Response

class StatusRemoteDataSource(
    private val service: StatusService,
    private val networkHandle: NetworkHandle
) {
    suspend fun queryStatus(): Response<StatusApiResult> {
        return if (networkHandle.networkMode().value == NetworkMode.WIFI) {
            service.queryStatus(ApiHostConstant.PUBLIC_API_HOST)
        } else {
            service.queryStatus(ApiHostConstant.PUBLIC_API_HOST)
        }
    }
}