package com.example.reflecgame

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reflecgame.database.ScoreBoard

fun yourScore(startTimeMilli:Long, endTimeMilli:Long):String {
   val reactionTime=endTimeMilli-startTimeMilli

    return "{reactionTimeMilli} seconds"
}

fun yourScoreFormatted(startTime:Long, endTime: Long, res:Resources): String{
    val reactionTime= endTime - startTime
    return res.getString(R.string.milliseconds, reactionTime)

}

fun formatScores(scores: List<ScoreBoard>, resources: Resources): Spanned{
    val sb = StringBuilder()
    sb.apply{
        append(resources.getString(R.string.your_score_is))
        scores.forEach{
            append("${it.endTime.minus(it.startTime)}")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)