package com.allen.migo.logic
import com.allen.migo.ext.getEndOfDayTimestamp
import java.util.concurrent.TimeUnit


class DayPassportProvider(): PassportProvider {
    override fun expiredTimestamp(num: Int, activateTimestamp: Long): Long {
        val expired = activateTimestamp + TimeUnit.DAYS.toMillis(num.toLong())
        return expired.getEndOfDayTimestamp()
    }

    override fun passType(): PassType {
        return PassType.DAY
    }
}