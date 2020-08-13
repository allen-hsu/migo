package com.allen.migo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allen.migo.data.StatusRepository
import kotlinx.coroutines.launch


class StatusViewModel(private val repository : StatusRepository): BaseViewModel() {


    private val _status = MutableLiveData(0)
    val status: LiveData<Int> get() = _status

    private val _message = MutableLiveData("")
    val message: LiveData<String> get() = _message

    fun getNetworkStatus() {
        viewModelScope.launch {
            repository.fetchData().let {
                if(it.isSuccessful) {
                    it.body()?.apply {
                        this.status?.apply {
                            _status.postValue(this)
                        }

                        this.message?.apply {
                            _message.postValue(this)
                        }
                    }
                }
            }
        }
    }
}