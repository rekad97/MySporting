package com.example.mysporting

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.models.MyResult
import com.example.mysporting.retrofit.ApiInterface
import com.example.mysporting.models.SportPlace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportPlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var recyclerView: RecyclerView
        lateinit var recyclerAdapter: SportPlacesRecyclerViewAdapter

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_places)
        val apiInterface = ApiInterface.create().getSportPlaces()
        apiInterface.enqueue( object : Callback<List<SportPlace>> {
            override fun onResponse(call: Call<List<SportPlace>>?, response: Response<List<SportPlace>>?) {
                recyclerView = findViewById(R.id.recyclerViewSportPlaces) as RecyclerView
                recyclerAdapter = SportPlacesRecyclerViewAdapter(this) { item -> showDialog(item)}

                recyclerView.adapter = recyclerAdapter
                Log.d("responsebody", response!!.toString())

                if(response!!.body() != null)
                    recyclerAdapter.setSportPlaceListItems(response.body()!!)

                recyclerAdapter.notifyDataSetChanged()
                val layoutManager = GridLayoutManager(this@SportPlacesActivity, 1)
                recyclerView.layoutManager = layoutManager
            }

            override fun onFailure(call: Call<List<SportPlace>>?, t: Throwable?) {

            }




        })
    }

    fun showDialog(sportPlace: SportPlace) {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_sport_place_details)
        val name =  dialog.findViewById(R.id.sportPlaceNameTextView) as TextView
        val city =  dialog.findViewById(R.id.cityTextView) as TextView
        val address = dialog.findViewById(R.id.addressTextView) as TextView

        name.text = sportPlace.name
        city.text = sportPlace.city
        address.text = sportPlace.address

        val noBtn = dialog.findViewById(R.id.cancelBtnDetails) as Button

        noBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

}