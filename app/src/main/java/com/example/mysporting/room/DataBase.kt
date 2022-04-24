package com.example.mysporting.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysporting.models.MyResult

@Database(entities = [MyResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myResultDao(): MyResultDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase{
            val tmpInstance = INSTANCE
            if(tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "results"
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}