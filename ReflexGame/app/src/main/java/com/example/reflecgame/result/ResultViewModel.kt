package com.example.reflecgame.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabaseDao
import com.example.reflecgame.game.GameViewModel

class ResultViewModel(private val scoreKey: Long= 0L, dataSource: ScoreDatabaseDao) : ViewModel() {

    val database = dataSource

    //there might be a problem because of this
   //private val scores: LiveData<List<ScoreBoard>>
    private val scores: LiveData<ScoreBoard>
    val scores2=database.getAllScores()

    fun getScores()= scores

    init {
        scores=database.getScoreWithId(scoreKey)
    }

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
    /*init {
       // _result.value=finalScore
        var score=finalresult.value

         Log.i("ResultViewModel", "Final score in results is $score")
    }*/

    private val _navigateToScore = MutableLiveData<Long?>()
    val navigateToScore
        get() = _navigateToScore

    fun onClickedScore(id: Long) {
        _navigateToScore.value = id
    }

    fun onScoreNavigated() {
        _navigateToScore.value = null
    }
}