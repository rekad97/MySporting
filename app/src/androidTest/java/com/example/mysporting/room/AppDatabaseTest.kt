package com.example.mysporting.room

import org.junit.Assert.*

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mysporting.models.MyResult
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class AppDatabaseTest : TestCase() {
    // get reference to the LanguageDatabase and LanguageDao class
    private lateinit var db: AppDatabase
    private lateinit var dao: MyResultDao


    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.myResultDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadMyResult() = runBlocking {
        val result = MyResult(123, "Futás", "2022-02-14", "12km")
        dao.add(result)
        val results = dao.getAll()
        assertTrue(results.value!!.contains(result))
    }

    @Test
    fun writeAndDeleteMyResult() = runBlocking {
        val result = MyResult(113, "Fekvőtámasz", "2022-02-14", "20db")
        dao.add(result)
        dao.delete(result)
        assertFalse(dao.getAll().value!!.contains(result))
    }

    @Test
    fun updateMyResult() = runBlocking {
        val result = MyResult(113, "Fekvőtámasz", "2022-02-14", "20db")
        dao.add(result)
        val updated_result = MyResult(113, "Fekvőtámasz", "2022-02-15", "22db")
        dao.update(updated_result)
        assertTrue(dao.getAll().value!!.contains(updated_result))
        assertFalse(dao.getAll().value!!.contains(result))
    }
}