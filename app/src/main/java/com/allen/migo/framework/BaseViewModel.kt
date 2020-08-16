package com.allen.migo.framework

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.jetbrains.annotations.TestOnly

abstract class BaseViewModel : ViewModel() {

    protected var viewModelJob: Job = Job()
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
