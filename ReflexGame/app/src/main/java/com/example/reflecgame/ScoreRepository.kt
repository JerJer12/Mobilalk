package com.example.reflecgame

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabase
import com.example.reflecgame.database.ScoreDatabaseDao


interface ScoreRepository {
    fun getAllScores(): LiveData<List<ScoreBoard>>
    fun getScoreWithId(id: Long): LiveData<ScoreBoard>
    suspend fun update(scoreBoard: ScoreBoard)
    suspend fun insert(scoreBoard: ScoreBoard)
    suspend fun clear()
    suspend fun getLastScore(): ScoreBoard?

}

//class RoomScoreRepository(private val databaseDao: ScoreDatabaseDao)

class RoomScoreRepository(private val databaseDao: ScoreDatabaseDao) : ScoreRepository {
    override fun getAllScores(): LiveData<List<ScoreBoard>> {
                return databaseDao.getAllScores()

    }

    override fun getScoreWithId(id: Long): LiveData<ScoreBoard> {
        return databaseDao.getScoreWithId(id)
    }

    override suspend fun update(scoreBoard: ScoreBoard) {
        databaseDao.update(scoreBoard)
    }

    override suspend fun insert(scoreBoard: ScoreBoard) {
        databaseDao.insert(scoreBoard)
    }

    override suspend fun clear() {
        databaseDao.clear()
    }

    override suspend fun getLastScore(): ScoreBoard? {
        return databaseDao.getLastScore()
    }


}
/*
fun createScoreRep(): ScoreRepository{
    return RoomScoreRepository()
}*/



/*
interface ScoreRepository {
    fun getScores(): List<Score>
    fun addScore(score: Score)
}

class ScoreRepositoryImpl(private val api: ScoreApi, private val cache: ScoreCache): ScoreRepository {
    override fun getScores(): List<Score> {
        val scores = cache.getScores()
        if (scores.isEmpty()) {
            val scoresFromApi = api.getScores()
            cache.saveScores(scoresFromApi)
            return scoresFromApi
        }
        return scores
    }

    override fun addScore(score: Score) {
        api.addScore(score)
        cache.saveScore(score)
    }
}*/
