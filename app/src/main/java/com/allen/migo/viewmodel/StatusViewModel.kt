package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.allen.migo.data.StatusRepository
import com.allen.migo.framework.BaseViewModel
import com.allen.migo.network.core.ApiResult

class StatusViewModel(private val repository: StatusRepository) : BaseViewModel() {

    private val _status = MutableLiveData(0)
    val status: LiveData<Int> get() = _status

    private val _message = MutableLiveData("")
    val message: LiveData<String> get() = _message


    fun getNetworkStatus() = liveData(viewModelScope.coroutineContext) {
        emit(ApiResult.loading(data = null))
        val response = repository.queryStatus()
        if (response.isSuccessful) {
            emit(ApiResult.success(data = response.body()))
            response.body()?.apply {
                this.status?.apply {
                    _status.postValue(this)
                }
                this.message?.apply {
                    _message.postValue(this)
                }
            }
        } else {
            emit(ApiResult.error(response.message(), data = null))
        }
    }
}