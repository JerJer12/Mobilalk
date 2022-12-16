package com.example.reflecgame.database

import androidx.room.Insert
import androidx.room.Update

interface ScoreDatabaseDao {
    @Insert
    suspend fun insert(score: Score)

    @Update
    suspend fun update(score: Score)
}