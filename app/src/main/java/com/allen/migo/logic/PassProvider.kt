package com.allen.migo.logic

interface PassportProvider {
    fun expiredTimestamp(num: Int, activateTimestamp: Long): Long
    fun passType(): PassType

}

class PassProviderFactory {
    companion object {
        fun factory(passType: PassType): PassportProvider {
            return if (passType == PassType.HOUR) {
                HourPassProvider()
            } else {
                DayPassProvider()
            }
        }
    }
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