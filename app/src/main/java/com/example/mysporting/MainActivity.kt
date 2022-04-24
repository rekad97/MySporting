package com.example.mysporting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.room.Room
import com.example.mysporting.room.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "results"
        ).build()
        val btnGoToMyResults = findViewById(R.id.btnGoToMyResults) as Button;
        val btnGoToSportPlaces = findViewById(R.id.btnGoToSportPlaces) as Button;

        btnGoToMyResults.setOnClickListener {
            val intent = Intent(this, MyResultsActivity::class.java);
            startActivity(intent);
        }

        btnGoToSportPlaces.setOnClickListener {
            val intent = Intent(this, SportPlacesActivity::class.java);
            startActivity(intent);
        }
    }


}