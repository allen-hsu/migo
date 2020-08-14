package com.allen.migo.logic

interface MemberPass {

    fun expiredDate(): String

    fun activateDate(): String

    fun insertDate(): String

    fun passType(): PassType

    fun status(): PassStatus

    fun activate()
}

enum class PassType {
    HOUR,
    DAY,
}

enum class PassStatus {
    NOT_ACTIVATE,
    ACTIVATE,
    EXPIRE,
}