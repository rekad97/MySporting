package com.example.mysporting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.retrofit.ApiInterface
import com.example.mysporting.models.SportPlace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportPlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var recyclerView: RecyclerView
        lateinit var recyclerAdapter: RecyclerViewAdapter

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_places)
        val apiInterface = ApiInterface.create().getSportPlaces("19.040,47.497" ,50)
        apiInterface.enqueue( object : Callback<List<SportPlace>> {
            override fun onResponse(call: Call<List<SportPlace>>?, response: Response<List<SportPlace>>?) {
                recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                recyclerAdapter = RecyclerViewAdapter(this)
                recyclerView.layoutManager = LinearLayoutManager(this@SportPlacesActivity)
                recyclerView.adapter = recyclerAdapter
                if(response!!.body() != null)
                    recyclerAdapter.setSportPlaceListItems(response.body()!!)


            }

            override fun onFailure(call: Call<List<SportPlace>>?, t: Throwable?) {

            }


        })
    }

}