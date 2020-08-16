package com.allen.migo.network.api

import com.google.gson.annotations.SerializedName

data class StatusApiResult(
    @field:SerializedName("status")
    var status: Int? = null,

    @field:SerializedName("message")
    var message: String? = null
)