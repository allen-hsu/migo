package com.allen.migo.logic

interface PassportProvider {
    fun expiredTimestamp(num: Int, activateTimestamp: Long): Long
    fun passType(): PassType
}

enum class PassType {
    HOUR,
    DAY,
    UNKNOWN,
}

enum class PassStatus {
    NOT_ACTIVATE,
    ACTIVATE,
    EXPIRE,
    UNKNOWN,
}