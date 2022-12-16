package com.example.reflecgame.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(finalScore: Long) : ViewModel() {

    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result


    init {
        _result.value=finalScore
    }
}