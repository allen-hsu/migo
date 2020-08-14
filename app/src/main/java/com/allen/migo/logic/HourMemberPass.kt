package com.allen.migo.logic

class HourMemberPass(unitNum: Int, addTime: Long) : AbsMemberPass(unitNum, addTime) {

    init {
        passType = PassType.HOUR
    }

    override fun expiredDate(): String {
        TODO("Not yet implemented")
    }

    override fun activateDate(): String {
        TODO("Not yet implemented")
    }

    override fun insertDate(): String {
        TODO("Not yet implemented")
    }
}