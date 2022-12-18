package com.example.reflecgame

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.reflecgame.R
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.databinding.ItemLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.coroutineScope

private val HEADER_VIEW_TYPE = 0
private val ITEM_VIEW_TYPE = 1

class MyAdapter(val clickListener: ScoreListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ScoreBoardDiffCallback()){
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")

        }
    }


    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object{
            fun from(parent: ViewGroup): TextViewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val view =layoutInflater.inflate(R.layout.header,parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ScoreBoard, clickListener: ScoreListener){
            binding.score=item
            binding.clickListener=clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent,false )
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is ViewHolder -> {
                val scoreItem = getItem(position) as DataItem.ScoreBoardItem
                holder.bind(scoreItem.scoreBoard, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return  when (getItem(position)){
            is DataItem.Header -> HEADER_VIEW_TYPE
            is DataItem.ScoreBoardItem -> ITEM_VIEW_TYPE
        }
    }

    fun addHeaderAndSummitList(list: List<ScoreBoard>?){
        adapterScope.launch {
            val items =when (list){
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ScoreBoardItem(it) }
            }
            withContext(Dispatchers.Main){
                submitList(items)
            }
        }
    }


}

class ScoreListener(val clickListener: (scoreId:Long) -> Unit) {
    fun onClick(score: ScoreBoard)=clickListener(score.scoreId)
}

sealed class DataItem {
    abstract val id: Long

    data class ScoreBoardItem(val scoreBoard: ScoreBoard): DataItem(){
        override val id = scoreBoard.scoreId
    }

    object Header: DataItem(){
        override val id = Long.MIN_VALUE
    }
}

class ScoreBoardDiffCallback: DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem:DataItem, newItem:DataItem):Boolean{
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}






/*
class MyAdapter(private val mList: List<ScoreBoard>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.reactionTime.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}*/
