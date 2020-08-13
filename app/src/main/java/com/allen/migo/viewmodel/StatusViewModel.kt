package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.MutableLiveData
import com.allen.migo.data.StatusRepository
import com.allen.migo.network.core.ApiResult
import kotlinx.coroutines.launch


class StatusViewModel(private val repository : StatusRepository): BaseViewModel() {

    private val _status = MutableLiveData(0)
    val status: LiveData<Int> get() = _status

    private val _message = MutableLiveData("")
    val message: LiveData<String> get() = _message


    fun getNetworkStatus() = liveData(viewModelScope.coroutineContext)  {
        emit(ApiResult.loading(data = null))
        val response = repository.queryStatus()
        if(response.isSuccessful) {
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
//    fun getNetworkStatus() {
//        viewModelScope.launch {
//
//            repository.fetchData().let {
//                if(it.isSuccessful) {
//                    it.body()?.apply {
//                        this.status?.apply {
//                            _status.postValue(this)
//                        }
//
//                        this.message?.apply {
//                            _message.postValue(this)
//                        }
//                    }
//                }
//            }
//        }
//    }
}