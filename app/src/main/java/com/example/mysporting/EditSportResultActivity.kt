package com.example.mysporting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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


abstract class EditSportResultActivity : AppCompatActivity() {

    private  var id: Int = 0;
    private lateinit var viewModel: MyResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sport_result)
        val btn = findViewById(R.id.editResultBtn) as Button;
        val modelFactory = MyResultViewModelFactory(application)

        Log.d("resultid", id.toString())
        viewModel = ViewModelProvider(this, modelFactory).get(MyResultViewModel::class.java)
        btn.setOnClickListener() {
            updateResult()
        }
    }

    private fun updateResult() {



        val nameText = findViewById(R.id.editTextActivityName) as TextView
        val name = nameText.text.toString()

        val dateInput = findViewById(R.id.editTextDate) as TextView
        val date = dateInput.text.toString()

        val notesText = findViewById(R.id.editTextNotes) as TextView
        val notes = notesText.text.toString()


        val result = MyResult((id),name, date, notes)
        viewModel.updateResult(result)

        Toast.makeText((this@EditSportResultActivity), "Successfully edited!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, MyResultsActivity::class.java);
        startActivity(intent);
    }

    private fun requireContext(addNewSportResultActivity: EditSportResultActivity): Context {
        return this
    }
}