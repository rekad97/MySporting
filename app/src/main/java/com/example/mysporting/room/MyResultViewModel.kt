package com.example.mysporting.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import com.example.mysporting.models.MyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyResultViewModel(application: Application) : ViewModel() {
    private val repository: MyResultRepository

    private val db:AppDatabase = AppDatabase.getDb(application)
    internal val allResults : LiveData<List<MyResult>> = db.myResultDao().getAll()
    init {
        val resultDao = AppDatabase.getDb(application).myResultDao()
        repository = MyResultRepository(resultDao)
    }



    fun addResult(result: MyResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addResult(result)
        }
    }

    fun updateResult(result: MyResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateResult(result)
        }
    }

    fun deleteResult(result: MyResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(result)
        }
    }
}