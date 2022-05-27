package com.example.mysporting.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "myresults")
data class MyResult(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "activity_name") val activityName: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "notes") val notes: String?
)