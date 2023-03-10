package com.example.reflecgame.game

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.reflecgame.ScoreRepository
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabaseDao
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import java.util.Random


class GameViewModel(dataSource: ScoreDatabaseDao, application: Application, scoreRepository: ScoreRepository) : ViewModel() {

    val scoreRep = scoreRepository


    val database = dataSource

    val scores = scoreRepository.getAllScores()

    private var lastScore = MutableLiveData<ScoreBoard?>()

    private var buttonControl = MutableLiveData<ScoreBoard?>()


    val pressButtonVisible = Transformations.map(buttonControl){
        null !=it
    }

    val readyButtonVisible = Transformations.map(buttonControl){
        null ==it
    }

    private suspend fun getLastScoreFromDatabase(): ScoreBoard? {
       // val score = scoreRepository.getLastScore()
        val score = scoreRep.getLastScore()
        //val score = database.getLastScore()
        /*if (score?.endTime != score?.startTime) {
            score = null
        }*/
        return score
    }

    private fun initializeLastScore() {
        viewModelScope.launch {
            lastScore.value=getLastScoreFromDatabase()
            buttonControl.value=null
        }
    }

  /* private suspend fun insert(score:ScoreBoard){
        database.insert(score)
       // scoreRep.insert(score)
    }*/

  /*  private suspend fun update(score:ScoreBoard){
        database.update(score)
        //scoreRep.update(score)
    }*/

    private val random=Random()


    val waitTime=random.nextInt(3000)+2000 //generate random number between 500 and 1500

    val _readygo = MutableLiveData<String>()
    val readygo: LiveData<String>
        get() = _readygo


    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result



   init {
       lateinit var scoreRepository : ScoreRepository
       initializeLastScore()
        _result.value = 0
       _readygo.value = "Ready"

    }
    private val _finalresult = MutableLiveData<Long>()
    val finalresult: LiveData<Long>
        get() = _finalresult



    var startTime = currentTimeMillis()
    //val newScore = ScoreBoard()
    fun onReady(){
        viewModelScope.launch{
            val newScore = ScoreBoard()
            newScore.startTime = currentTimeMillis()

           // insert(newScore)
            scoreRep.insert(newScore)
            lastScore.value= getLastScoreFromDatabase()
            buttonControl.value =getLastScoreFromDatabase()

        //_readygo.value ="Ready"
        Thread.sleep(waitTime.toLong())
        startTime = currentTimeMillis()
        _readygo.value = "Go"
        }
    }

    fun onPress(){

        viewModelScope.launch {
            val oldScore= lastScore.value ?: return@launch
            oldScore.endTime= currentTimeMillis()
            oldScore.reactionTime= currentTimeMillis() - startTime
            //update(oldScore)
            scoreRep.update(oldScore)
            buttonControl.value =null


        }

        _readygo.value ="Ready"
        val endTime= currentTimeMillis()
        _result.value = endTime - startTime// -(waitTime.toLong())
        val finalscore=endTime - startTime //- (waitTime.toLong())
        _finalresult.value=endTime - startTime //- (waitTime.toLong())
        Log.i("ResultViewModel", "Final score is $finalscore ")
        Log.i("ResultViewModel", "Final result is $finalresult ")
    }


}



