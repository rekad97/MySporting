package com.example.mysporting

import android.content.Intent
import android.text.Layout
import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.models.MyResult
import com.example.mysporting.models.SportPlace
import com.example.mysporting.room.MyResultDao
import retrofit2.Callback

class MyResultsRecyclerViewAdapter(val context: List<MyResult>) : RecyclerView.Adapter<MyResultsRecyclerViewAdapter.MyResultViewHolder>() {

    var results : List<MyResult> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_my_result,parent,false)
        return MyResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: MyResultViewHolder, position: Int) {
        holder.myResultName.text = results.get(position).activityName
        holder.myResultDate.text = results.get(position).date
        holder.myResultNotes.text = results.get(position).notes


    }


    fun setResultsListItems(results: List<MyResult>){
        this.results = results;
        notifyDataSetChanged()
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    class MyResultViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val myResultName: TextView = itemView!!.findViewById(R.id.myResultName)
        val myResultDate: TextView = itemView!!.findViewById(R.id.myResultDate)
        val myResultNotes: TextView = itemView!!.findViewById(R.id.myResultNotes)


    }
}
