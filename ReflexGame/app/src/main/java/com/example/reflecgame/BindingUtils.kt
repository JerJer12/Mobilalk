package com.example.reflecgame

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.reflecgame.database.ScoreBoard

@BindingAdapter("scoreFormatted")
fun TextView.setScoreFormatted(item: ScoreBoard?){
    item?.let {
        text= yourScoreFormatted(item.startTime,item.endTime, context.resources)
    }
}