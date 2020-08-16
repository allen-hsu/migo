package com.allen.migo.viewmodel

import com.allen.migo.data.PassRepository
import com.allen.migo.framework.BaseViewModel
import com.allen.migo.logic.Pass
import com.allen.migo.logic.PassProviderFactory
import com.allen.migo.logic.PassType

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