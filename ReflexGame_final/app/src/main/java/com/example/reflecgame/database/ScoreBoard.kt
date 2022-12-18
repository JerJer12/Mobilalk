package com.example.reflecgame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scoreboard")
data class ScoreBoard(
    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,

    @ColumnInfo(name = "start_time")
    var startTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    var endTime: Long = startTime,

    @ColumnInfo(name = "reaction_time")
    var reactionTime: Long = endTime-startTime

)
