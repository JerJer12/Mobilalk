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

    val random=Random()

    val backgroundColor = MutableLiveData<Int>()
    /*val backgroundColor: LiveData<Int>
        get() = _backgroundColor*/

   // gameViewModel.backgroundColor.value = Color.parseColor("#FF0000")

    var round=0
    val maxround=1

    val waitTime=random.nextInt(3000)+2000 //generate random number between 500 and 1500

    val _readygo = MutableLiveData<String>()
    val readygo: LiveData<String>
        get() = _readygo
/*
    fun StartGame(){
        Thread.sleep(waitTime.toLong())
        _readygo.value = "Go"


    }*/

    //val database = dataSource
    val startTime = currentTimeMillis()
    //var endTime: Long = startTimeMilli

    companion object {
       // private var time_start=0L
      //  private var time_end=0L

    }
    private val _result = MutableLiveData<Long>()
    val result: LiveData<Long>
        get() = _result

    //private val timer= Timer()




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


    fun onReady(){
      //  time_start=System.currentTimeMillis()
       // _readygo.value ="Ready"
        Thread.sleep(waitTime.toLong())
        _readygo.value = "Go"
    }

    fun onPress(){
       // time_end=System.currentTimeMillis()-time_start
        val endTime= System.currentTimeMillis()
        val reactionTime = endTime- startTime
    }


}