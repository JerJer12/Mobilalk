package com.example.reflecgame

fun yourScore(startTimeMilli:Long, endTimeMilli:Long):String {
   val reactionTimeMilli=endTimeMilli-startTimeMilli

    return "{reactionTimeMilli} seconds"
}