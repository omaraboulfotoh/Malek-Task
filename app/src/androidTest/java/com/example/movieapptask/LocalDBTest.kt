package com.example.movieapptask

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.movieappkotlin.model.LocalDatabase
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.MovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDBTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: LocalDatabase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        movieDao = db.movieDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `insertAllAndAssertTahtTheFirstTitleIsEqual`() = runBlocking(Dispatchers.Unconfined) {
        val movies = listOf(Movie(title = "title1"), Movie(title = "title2"))
            movieDao.insertAll(movies)
        val dataSourceFactory = movieDao.getAll()
        val listOfMovies = (dataSourceFactory.create() as LimitOffsetDataSource).loadRange(0, 2)
        Assert.assertEquals(listOfMovies[0].title, "title1")
    }
}

