package com.example.reflecgame.result

import android.util.Log
import androidx.lifecycle.*
import com.example.reflecgame.ScoreRepository
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabaseDao
import kotlinx.coroutines.launch

class ResultViewModel(private val scoreKey: Long= 0L, dataSource: ScoreDatabaseDao) : ViewModel() {

   // private lateinit var scoreRepository : ScoreRepository

    val database = dataSource

    private suspend fun clear() {
        database.clear()
       // scoreRepository.clear()
    }

    //there might be a problem because of this
   //private val scores: LiveData<List<ScoreBoard>>
    private val scores: LiveData<ScoreBoard>
    val scores2=database.getAllScores()

    init {
        scores=database.getScoreWithId(scoreKey)

    }

    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result


    val clearButtonVisible = Transformations.map(scores2) {
        it?.isNotEmpty()
    }

    fun onDeleteAll(){
        viewModelScope.launch {
           // scoreRepository.clear()
            clear()
        }

    }
}