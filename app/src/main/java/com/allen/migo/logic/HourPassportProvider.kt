package com.allen.migo.logic

import java.util.concurrent.TimeUnit

class HourPassportProvider(): PassportProvider {
    override fun expiredTimestamp(num: Int, activateTimestamp: Long): Long {
        return activateTimestamp + TimeUnit.HOURS.toMillis(num.toLong())
    }

    override fun passType(): PassType {
        return PassType.HOUR
    }
}