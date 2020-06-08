package com.example.movieappkotlin.model

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAll(): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun delete(id:Int)

    @Query("DELETE FROM movie_table")
    fun deleteAll()

    @Update
    fun update(movie: Movie)
}