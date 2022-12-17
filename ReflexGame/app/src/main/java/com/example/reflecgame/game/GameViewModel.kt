package com.example.reflecgame.game

import android.app.Application
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*
import android.util.Log
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ColumnInfo
import android.os.CountDownTimer
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reflecgame.R
import com.example.reflecgame.database.ScoreDatabaseDao
import java.lang.System.currentTimeMillis
import kotlin.time.measureTime
import java.util.Random
import android.widget.LinearLayout




//import kotlinx.android.synthetic.main.activity_main.*

class GameViewModel(
    //dataSource: ScoreDatabaseDao,
    //application: Application
) : ViewModel() {

    private val random=Random()

    val backgroundColor = MutableLiveData<Int>()

    val waitTime=random.nextInt(3000)+2000 //generate random number between 500 and 1500

    val _readygo = MutableLiveData<String>()
    val readygo: LiveData<String>
        get() = _readygo

    //val database = dataSource
    //val startTime = currentTimeMillis()
    //var endTime: Long = startTimeMilli


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
        _result.value = 0

    }
    val _finalresult = MutableLiveData<Long>()
    val finalresult: LiveData<Long>
        get() = _finalresult

    var startTime = currentTimeMillis()
    fun onReady(){

        //_readygo.value ="Ready"
        Thread.sleep(waitTime.toLong())
        startTime = currentTimeMillis()
        _readygo.value = "Go"
    }

    fun onPress(){
        _readygo.value ="Ready"
        val endTime= currentTimeMillis()
        //_readygo.value = (result.value)?.plus(1).toString()
        _result.value = endTime - startTime// -(waitTime.toLong())
        val finalscore=endTime - startTime //- (waitTime.toLong())
        _finalresult.value=endTime - startTime //- (waitTime.toLong())
        _eventGameEnd.value = true
        Log.i("ResultViewModel", "Final score is $finalscore ")
        Log.i("ResultViewModel", "Final result is $finalresult ")
    }


}