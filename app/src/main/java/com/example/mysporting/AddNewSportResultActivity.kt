package com.example.mysporting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.mysporting.models.MyResult
import com.example.mysporting.room.MyResultViewModel
import java.util.*

class AddNewSportResultActivity : AppCompatActivity() {

    private lateinit var viewModel: MyResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_sport_result)
        viewModel = ViewModelProvider(this).get(MyResultViewModel::class.java)
        val btn = findViewById(R.id.addResultBtn) as Button;

        btn.setOnClickListener() {
            insertResultTDb()
        }
    }

    private fun insertResultTDb() {
        val nameText = findViewById(R.id.editTextActivityName) as TextView
        val name = nameText.text.toString();

        val dateInput = findViewById(R.id.editTextDate) as TextView
        val date = dateInput.text.toString()

        val notesText = findViewById(R.id.editTextNotes) as TextView
        val notes = notesText.text.toString()

        val result = MyResult(0, name, date, notes)
        viewModel.addResult(result)

        Toast.makeText(requireContext(this), "Successfully added!", Toast.LENGTH_LONG).show()
    }

    private fun requireContext(addNewSportResultActivity: AddNewSportResultActivity): Context {
        return this
    }
}