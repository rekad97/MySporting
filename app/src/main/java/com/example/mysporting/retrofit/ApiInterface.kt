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

    @GET("places")
    fun getSportPlaces(@Query("origin") location: String, @Query("radius") radius: Number) : Call<List<SportPlace>>
    companion object {

        var BASE_URL = "https://sportplaces.api.decathlon.com/api/v1/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}