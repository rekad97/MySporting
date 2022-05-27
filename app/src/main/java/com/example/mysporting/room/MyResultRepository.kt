package com.example.mysporting.room

import androidx.lifecycle.LiveData
import com.example.mysporting.models.MyResult

class MyResultRepository(private val resultDao: MyResultDao) {
    val results: LiveData<List<MyResult>> = resultDao.getAll()

    fun addResult(result: MyResult) {
        resultDao.add(result)
    }

    fun updateResult(result: MyResult) {
        resultDao.update(result)
    }

    fun deleteResult(result: MyResult) {
        resultDao.delete(result)
    }
}