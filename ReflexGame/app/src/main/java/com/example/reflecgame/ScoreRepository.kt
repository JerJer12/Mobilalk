package com.example.reflecgame

import androidx.lifecycle.LiveData
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabase


class ScoreRepository(private val database: ScoreDatabase) {


    fun getAllScores(): LiveData<List<ScoreBoard>> {
                return database.scoreDatabaseDao.getAllScores()

    }


    fun getScoreWithId(id: Long): LiveData<ScoreBoard> {
        return database.scoreDatabaseDao.getScoreWithId(id)
    }

    suspend fun update(scoreBoard: ScoreBoard) {
        database.scoreDatabaseDao.update(scoreBoard)
    }

    suspend fun insert(scoreBoard: ScoreBoard) {
        database.scoreDatabaseDao.insert(scoreBoard)
    }

    suspend fun clear() {
        database.scoreDatabaseDao.clear()
    }

    suspend fun getLastScore(): ScoreBoard? {
        return database.scoreDatabaseDao.getLastScore()
    }


}
