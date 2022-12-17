package com.example.reflecgame.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reflecgame.game.GameViewModel

class ResultViewModel(finalScore: Long) : ViewModel() {

    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result




    val _finalresult = MutableLiveData<Long>()
    val finalresult: LiveData<Long>
        get() = _finalresult


    fun startObserving(gameViewModel: GameViewModel){
        gameViewModel.finalresult.observeForever{score ->
        _finalresult.value = score}
        Log.i("ResultViewModel", "Final score in resultviewmodel is $finalresult.value ")
    }
    init {
        _result.value=finalScore
        var score=finalresult.value

         Log.i("ResultViewModel", "Final score in results is $score")
    }

}