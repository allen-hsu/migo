package com.allen.migo.logic

import com.allen.migo.ext.convertToFullDateTime

class Passport private constructor(private val unitNum: Int,
                                   private val provider: PassportProvider?){


    private var _activateTimestamp = 0L
    val activateTimestamp: Long get() = _activateTimestamp
    private var _expiredTimestamp = 0L
    val expiredTimestamp: Long get() = _expiredTimestamp
    private val _insertTimestamp = System.currentTimeMillis()
    val insertTimestamp: Long get() = _insertTimestamp

    data class Builder(
        private var unitNum: Int = 0,
        private var provider: PassportProvider? = null) {

        fun unitNum(unitNum: Int) = apply { this.unitNum = unitNum }
        fun provider(provider: PassportProvider) = apply { this.provider = provider }
        fun build() = Passport(unitNum, provider)
    }

    fun expiredDateTime(): String {
        return _expiredTimestamp.convertToFullDateTime()
    }

    fun activateDateTime(): String {
        return _activateTimestamp.convertToFullDateTime()
    }

    fun insertDateTime(): String {
        return _insertTimestamp.convertToFullDateTime()
    }

    fun passType(): PassType {
        provider?.run {
            return this.passType()
        }
        return PassType.UNKNOWN
    }

    fun status(): PassStatus {
        val currentTimestamp = System.currentTimeMillis()
        if(_activateTimestamp <= 0) {
            return PassStatus.NOT_ACTIVATE
        }

        if(currentTimestamp in _activateTimestamp.._expiredTimestamp) {
            return PassStatus.ACTIVATE
        }

        if(currentTimestamp > _expiredTimestamp) {
            return PassStatus.EXPIRE
        }
        return PassStatus.UNKNOWN
    }

    fun activate() {
        if(unitNum == 0) {
            //unitNum should not be zero
            return
        }

        if(_activateTimestamp <= 0) {
            _activateTimestamp = System.currentTimeMillis()
            provider?.run {
                _expiredTimestamp = this.expiredTimestamp(unitNum, _activateTimestamp)
            }
        }
    }
}

