package com.allen.migo.logic

class MockExpiredPassportProvider(): PassportProvider {
    override fun expiredTimestamp(num: Int, activateTimestamp: Long): Long {
        return activateTimestamp + 100
    }

    override fun passType(): PassType {
        return PassType.DAY
    }
}