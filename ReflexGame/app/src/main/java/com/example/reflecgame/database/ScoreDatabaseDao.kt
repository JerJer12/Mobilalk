package com.example.reflecgame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDatabaseDao {
    @Insert
    suspend fun insert(score: ScoreBoard)

    @Update
    suspend fun update(score: ScoreBoard)

    @Query("DELETE FROM scoreboard")
    suspend fun clear()

    @Query("SELECT * FROM scoreboard ORDER BY scoreId DESC")
    fun getAllScores(): LiveData<List<ScoreBoard>>
}