package com.allen.migo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly

abstract class BaseViewModel : ViewModel() {

    protected var viewModelJob : Job = Job()
    protected val viewModelScope by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob)
    }


    @TestOnly
    fun cancel() {
        onCleared()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
