package com.example.movieapptask.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.TmdbResponse

interface MoviesRepository {
    suspend fun getMoviesOnline(pageNumber: Int) : TmdbResponse?
    fun getMoviesOffline(): DataSource.Factory<Int, Movie>
    suspend fun saveAllMovies(movies: List<Movie>)
    fun getMovies(): LiveData<PagedList<Movie>>
}