package com.allen.migo.data

import com.allen.migo.network.api.StatusApiResult
import retrofit2.Response

class StatusRepository(private val remoteDataSource: StatusRemoteDataSource) {
    suspend fun queryStatus(): Response<StatusApiResult> {
        return remoteDataSource.queryStatus()
    }
}