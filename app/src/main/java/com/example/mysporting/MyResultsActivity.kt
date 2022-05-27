package com.example.mysporting

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysporting.databinding.ActivityMyResultsBinding
import com.example.mysporting.models.MyResult
import com.example.mysporting.room.MyResultViewModel
import com.example.mysporting.room.MyResultViewModelFactory
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MyResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyResultsBinding
    private lateinit var recyclerView: RecyclerView;
    private lateinit var viewModel: MyResultViewModel;
    private lateinit var resultAdapter: MyResultsRecyclerViewAdapter
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var tracker: GoogleAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics


        binding = ActivityMyResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddNewSportResultActivity::class.java);
            startActivity(intent);
        }

        recyclerView=findViewById(R.id.recyclerViewMyResults) as RecyclerView

        val modelFactory = MyResultViewModelFactory(application)
        viewModel = ViewModelProvider(this, modelFactory).get(MyResultViewModel::class.java)

        viewModel.allResults.observe(this, Observer { results ->
            resultAdapter = MyResultsRecyclerViewAdapter(results)
            resultAdapter.setResultsListItems(results)
            recyclerView.adapter= resultAdapter;
            resultAdapter.notifyDataSetChanged()
            val layoutManager = GridLayoutManager(this, 1)
            recyclerView.layoutManager = layoutManager;
        })

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            1,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val result =  resultAdapter.results.get(pos)

                if(direction == ItemTouchHelper.RIGHT) {
                    viewModel.deleteResult(result)
                    Toast.makeText((this@MyResultsActivity), "Successfully deleted!", Toast.LENGTH_LONG).show()
                }

                if(direction == ItemTouchHelper.LEFT){
                    showDialog(result)

                }

            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewMyResults)
        }


    }
    fun showDialog(result: MyResult) {
        var newName =""
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_edit_sport_result)
        val name =  dialog.findViewById(R.id.editTextActivityName) as TextView
        val date =  dialog.findViewById(R.id.editTextDate) as TextView
        val notes = dialog.findViewById(R.id.editTextNotes) as TextView

        name.text = result.activityName
        date.text = result.date
        notes.text = result.notes

        if(name.text.toString() != result.activityName) {
            newName = name.text.toString()
        }

        val yesBtn = dialog.findViewById(R.id.editResultBtn) as Button
        val noBtn = dialog.findViewById(R.id.cancelBtn) as Button
        yesBtn.setOnClickListener {

            val name = name.text.toString()
            val date = date.text.toString()
            val notes = notes.text.toString()
            viewModel.updateResult(MyResult(result.uid, name, date, notes))
            Toast.makeText((this@MyResultsActivity), "Successfully updated!", Toast.LENGTH_LONG).show()
            dialog.dismiss()
            resultAdapter.refresh()
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
            resultAdapter.refresh()
        }
        dialog.show()

    }

}