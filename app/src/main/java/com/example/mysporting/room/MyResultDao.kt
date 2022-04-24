package com.example.mysporting.room

import androidx.room.*
import com.example.mysporting.models.MyResult

@Dao
    interface MyResultDao {
        @Query("SELECT * FROM results")
        fun getAll(): List<MyResult>

        @Update()
        fun update(result: MyResult)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun add( result: MyResult)

        @Delete
        fun delete(result: MyResult)
    }