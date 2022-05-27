package com.example.mysporting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.room.Room
import com.example.mysporting.room.AppDatabase
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker

class MainActivity : AppCompatActivity() {
    private lateinit var analytics: GoogleAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGoToMyResults = findViewById(R.id.btnGoToMyResults) as Button;
        val btnGoToSportPlaces = findViewById(R.id.btnGoToSportPlaces) as Button;

        btnGoToMyResults.setOnClickListener {
            val intent = Intent(this, MyResultsActivity::class.java);
            startActivity(intent);
            getDefaultTracker()
        }

        btnGoToSportPlaces.setOnClickListener {
            val intent = Intent(this, SportPlacesActivity::class.java);
            startActivity(intent);
        }
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    @Synchronized
    fun getDefaultTracker(): Tracker? {
        var tracker: Tracker? = null
        analytics = GoogleAnalytics.getInstance(this) // here pass your activity instance
        analytics?.let {
            tracker = it.newTracker(R.xml.global_tracker)
        }
        return tracker
    }


}