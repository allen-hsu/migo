package com.allen.migo.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface StatusService {
    @GET("{fullUrl}/status")
    suspend fun getStatus(@Path(value = "fullUrl", encoded = true) url: String?): Response<StatusApiResult>
}