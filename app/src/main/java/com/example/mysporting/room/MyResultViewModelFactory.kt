package com.example.mysporting.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyResultViewModelFactory(var application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyResultViewModel::class.java)) {
            return MyResultViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
