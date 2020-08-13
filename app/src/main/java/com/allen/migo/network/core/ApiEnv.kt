package com.allen.migo.network.core

import com.allen.migo.constant.ApiHostConstant

enum class ApiEnv(val value: String) {
    PUBLIC(ApiHostConstant.PUBLIC_API_HOST),
    PRIVATE(ApiHostConstant.PRIVATE_API_HOST)
}