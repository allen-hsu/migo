package com.allen.migo.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface StatusService {
    @GET("{fullUrl}/status")
    suspend fun queryStatus(
        @Path(
            value = "fullUrl",
            encoded = true
        ) url: String?
    ): Response<StatusApiResult>
}