package com.example.mysporting.retrofit

import com.example.mysporting.models.SportPlace
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("sportplaces")
    fun getSportPlaces() : Call<List<SportPlace>>
    companion object {

        var BASE_URL = "https://sportplacesapi.herokuapp.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}