package com.allen.migo.viewmodel

import com.allen.migo.data.PassRepository
import com.allen.migo.framework.BaseViewModel
import com.allen.migo.logic.*

class WalletViewModel(private val repository: PassRepository) : BaseViewModel() {

    fun addPass(passNum: Int, passType: PassType) {
        val pass = Pass.Builder()
            .unitNum(passNum)
            .provider(
                PassProviderFactory.factory(passType)
            ).build()
        repository.addPass(pass)
    }
}