package com.allen.migo.data

import androidx.lifecycle.LiveData
import com.allen.migo.logic.Pass

class PassRepository(private val dataSource: PassDataSource) {

    fun queryPassList(): LiveData<List<Pass>> {
        return dataSource.queryPassList()
    }

    fun activatePass(pass: Pass) {
        dataSource.activatePass(pass)
    }

    fun addPass(pass: Pass) {
        dataSource.addPass(pass)
    }
}