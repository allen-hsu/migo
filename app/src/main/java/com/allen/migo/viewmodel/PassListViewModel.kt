package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import com.allen.migo.data.PassRepository
import com.allen.migo.framework.BaseViewModel
import com.allen.migo.logic.Pass
import com.allen.migo.logic.PassProviderFactory
import com.allen.migo.logic.PassType


class PassListViewModel(private val repository: PassRepository) : BaseViewModel() {

    fun queryPassList(): LiveData<List<Pass>> {
        return repository.queryPassList()
    }

    fun activatePass(pass: Pass) {
        repository.activatePass(pass)
    }

    fun addPass(passNum: Int, passType: PassType) {
        val pass = Pass.Builder()
            .unitNum(passNum)
            .provider(
                PassProviderFactory.factory(passType)
            ).build()
        repository.addPass(pass)
    }

    fun getLatestExpiredTime(): LiveData<Long> {
        return repository.getLatestExpiredTime()
    }
}