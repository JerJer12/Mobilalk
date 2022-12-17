package com.example.reflecgame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scoreboard")
data class ScoreBoard(
    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,

    @ColumnInfo(name = "reaction_time")
    val reactionTime: Long

)
