package com.allen.migo.logic

import com.allen.migo.ext.getDate

abstract class AbsMemberPass(protected val unitNum: Int, protected val addTime: Long): MemberPass {

    protected var status = PassStatus.NOT_ACTIVATE
    protected var passType =  PassType.DAY

    abstract override fun expiredDate(): String

    abstract override fun activateDate(): String

    override fun insertDate(): String {
        return addTime.getDate()
    }

    override fun passType(): PassType {
        return passType
    }

    override fun status(): PassStatus {
        return status
    }

    abstract override fun activate()
}