package com.example.mysporting

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.models.SportPlace
import retrofit2.Callback

class SportPlacesRecyclerViewAdapter(val context: Callback<List<SportPlace>>,val listener: (SportPlace) -> Unit) : RecyclerView.Adapter<SportPlacesRecyclerViewAdapter.SportPlaceViewHolder>() {

    var sportPlaceList : List<SportPlace> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportPlaceViewHolder {

        Log.d("sportadapter", "create")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_sport_place,parent,false)
        return SportPlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("count", sportPlaceList.size.toString())
        return sportPlaceList.size
    }

    override fun onBindViewHolder(holder: SportPlaceViewHolder, position: Int) {

        val item = sportPlaceList[position]
        Log.d("sportadapter", "bind")
        holder.sportPlaceName.text = sportPlaceList.get(position).name
        holder.itemView.setOnClickListener{
            listener(item)
        }
    }

    fun setSportPlaceListItems(sportPlaceList: List<SportPlace>){
        Log.d("sportplaces", sportPlaceList.toString())
        this.sportPlaceList = sportPlaceList;
        notifyDataSetChanged()
    }

    class SportPlaceViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val sportPlaceName: TextView = itemView!!.findViewById(R.id.myResultName)

    }
}
