package com.example.reflecgame.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reflecgame.database.ScoreDatabaseDao

class GameViewModelFactory(
    private val dataSource: ScoreDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(GameViewModel::class.java)) {
                return GameViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}