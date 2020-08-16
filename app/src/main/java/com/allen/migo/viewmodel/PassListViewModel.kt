package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import com.allen.migo.data.PassRepository
import com.allen.migo.framework.BaseViewModel
import com.allen.migo.logic.Pass


class PassListViewModel(private val repository: PassRepository) : BaseViewModel() {

    fun queryPassList(): LiveData<List<Pass>> {
        return repository.queryPassList()
    }

    fun activatePass(pass: Pass) {
        repository.activatePass(pass)
    }
}