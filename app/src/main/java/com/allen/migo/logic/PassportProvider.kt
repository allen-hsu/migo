package com.allen.migo.logic

interface PassportProvider {
    fun expiredTimestamp(num: Int, activateTimestamp: Long): Long
    fun passType(): PassType
}

enum class PassType {
    UNKNOWN,
    HOUR,
    DAY,
}

enum class PassStatus {
    UNKNOWN,
    NOT_ACTIVATE,
    ACTIVATE,
    EXPIRE,
}