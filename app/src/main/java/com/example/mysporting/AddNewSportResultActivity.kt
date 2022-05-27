package com.example.mysporting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mysporting.models.MyResult
import com.example.mysporting.room.MyResultViewModel
import com.example.mysporting.room.MyResultViewModelFactory
import java.util.*


class AddNewSportResultActivity : AppCompatActivity() {

    private lateinit var viewModel: MyResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_sport_result)
        val btn = findViewById(R.id.addResultBtn) as Button;
        val modelFactory = MyResultViewModelFactory(application)
        viewModel = ViewModelProvider(this, modelFactory).get(MyResultViewModel::class.java)
        btn.setOnClickListener() {
            insertResultTDb()
        }
    }

    private fun insertResultTDb() {
        val nameText = findViewById(R.id.addTextActivityName) as TextView
        val name = nameText.text.toString();

        val dateInput = findViewById(R.id.addTextDate) as TextView
        val date = dateInput.text.toString()

        val notesText = findViewById(R.id.addTextNotes) as TextView
        val notes = notesText.text.toString()

        val result = MyResult(0, name, date, notes)
        viewModel.addResult(result)

        Toast.makeText(requireContext(this), "Successfully added!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, MyResultsActivity::class.java);
        startActivity(intent);
    }

    private fun requireContext(addNewSportResultActivity: AddNewSportResultActivity): Context {
        return this
    }
}