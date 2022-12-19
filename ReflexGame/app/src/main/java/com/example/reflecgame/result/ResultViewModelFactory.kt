package com.example.reflecgame.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reflecgame.ScoreRepository
import com.example.reflecgame.database.ScoreDatabaseDao

class ResultViewModelFactory(
    private val scoreKey: Long,
    private val dataSource: ScoreDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(scoreKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}