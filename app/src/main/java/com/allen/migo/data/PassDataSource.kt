package com.allen.migo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allen.migo.logic.MockExpiredPassProvider
import com.allen.migo.logic.Pass


class PassLocalDataSource : PassDataSource {
    private val passListData: MutableList<Pass> = mutableListOf()
    private val passListLiveData: MutableLiveData<List<Pass>> = MutableLiveData(passListData)

    init {
        createMockData()
    }

    override fun queryPassList(): LiveData<List<Pass>> {
        return passListLiveData
    }

    override fun activatePass(pass: Pass) {
        val value = passListData.find {
            it == pass
        }
        value?.activate()
        passListLiveData.postValue(passListData)
    }

    private fun createMockData() {
//        for (i in 0..5) {
//            _mockListData.apply {
//                val pass = Pass.Builder()
//                    .provider(HourPassProvider())
//                    .unitNum((i + 1))
//                    .build()
//                this.add(pass)
//            }
//        }
//
//        for (i in 0..5) {
//            _mockListData.apply {
//                val pass = Pass.Builder()
//                    .provider(DayPassProvider())
//                    .unitNum((i + 1))
//                    .build()
//                this.add(pass)
//            }
//        }

        for (i in 0..5) {
            passListData.apply {
                val pass = Pass.Builder()
                    .provider(MockExpiredPassProvider())
                    .unitNum((i + 1))
                    .build()
                this.add(pass)
            }
        }
    }
}

interface PassDataSource {
    fun activatePass(pass: Pass)
    fun queryPassList(): LiveData<List<Pass>>
}