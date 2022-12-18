package com.example.reflecgame.game

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.reflecgame.R
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabaseDao
import com.example.reflecgame.formatScores
import com.example.reflecgame.yourScoreFormatted
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import java.util.Random


//import kotlinx.android.synthetic.main.activity_main.*

class GameViewModel(dataSource: ScoreDatabaseDao, application: Application, private val scoreKey: Long=0L) : ViewModel() {

    val database = dataSource

    val scores = database.getAllScores()

    private var lastScore = MutableLiveData<ScoreBoard?>()


    val scoreString= Transformations.map(scores) {scores ->
        formatScores(scores, application.resources)
    }

    val readyButtonVisible = Transformations.map(lastScore){
        null !==it
    }

    private suspend fun getLastScoreFromDatabase(): ScoreBoard? {
        var score = database.getLastScore()
        /*if (score?.endTime != score?.startTime) {
            score = null
        }*/
        return score
    }

    private fun initializeLastScore() {
        viewModelScope.launch { lastScore.value=getLastScoreFromDatabase() }
    }

    private suspend fun insert(score:ScoreBoard){
        database.insert(score)
    }

    private suspend fun update(score:ScoreBoard){
        database.update(score)
    }

    private suspend fun clear() {
        database.clear()
    }


    private val random=Random()


    val waitTime=random.nextInt(3000)+2000 //generate random number between 500 and 1500

    val _readygo = MutableLiveData<String>()
    val readygo: LiveData<String>
        get() = _readygo


    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result


    private val _eventGameEnd= MutableLiveData<Boolean>()
    val eventGameEnd: LiveData<Boolean>
        get() = _eventGameEnd

    fun onGameEnd(){
        _eventGameEnd.value = true
    }

    fun onGameEndComp(){
        _eventGameEnd.value = false
    }

   init {
       initializeLastScore()
        _result.value = 0
       _readygo.value = "Ready"

    }
    val _finalresult = MutableLiveData<Long>()
    val finalresult: LiveData<Long>
        get() = _finalresult

    private val _pressEnabled= MutableLiveData<Boolean>()
    val pressEnabled: LiveData<Boolean>
        get() = _pressEnabled

    private val _readyEnabled= MutableLiveData<Boolean>()
    val readyEnabled: LiveData<Boolean>
        get() = _readyEnabled

    private val _navToScore = MutableLiveData<ScoreBoard>()
    val navToScore: LiveData<ScoreBoard>
        get() = _navToScore

    var startTime = currentTimeMillis()
    //val newScore = ScoreBoard()
    fun onReady(){
        viewModelScope.launch{
            val newScore = ScoreBoard()
            newScore.startTime = currentTimeMillis()

            insert(newScore)
            lastScore.value= getLastScoreFromDatabase()

        _readyEnabled.value= false
        _pressEnabled.value=true
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
            update(oldScore)
            //newScore.endTime= currentTimeMillis()
            //insert(newScore)
            //update(newScore)
            //_navToScore.value =oldScore

        }
        _readyEnabled.value= true
        _pressEnabled.value=false
        _readygo.value ="Ready"
        val endTime= currentTimeMillis()
        _result.value = endTime - startTime// -(waitTime.toLong())
        val finalscore=endTime - startTime //- (waitTime.toLong())
        _finalresult.value=endTime - startTime //- (waitTime.toLong())
        _eventGameEnd.value = true
        Log.i("ResultViewModel", "Final score is $finalscore ")
        Log.i("ResultViewModel", "Final result is $finalresult ")
    }
    fun onDeleteAll(){
        viewModelScope.launch {
            clear()

            lastScore.value=null
        }

    }

}


