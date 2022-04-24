package com.example.mysporting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.models.SportPlace
import retrofit2.Callback

class RecyclerViewAdapter(val context: Callback<List<SportPlace>>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var sportPlaceList : List<SportPlace> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        print("adapter 1. cretae")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_sport_places,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sportPlaceList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        print("adapter 2. onbind")
        holder.sportPlaceName.text = sportPlaceList.get(position).slug

    }

    fun setSportPlaceListItems(sportPlaceList: List<SportPlace>){
        print(sportPlaceList)
        this.sportPlaceList = sportPlaceList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val sportPlaceName: TextView = itemView!!.findViewById(R.id.sportPlaceName)

    }
}
