package com.example.movieapptask.mocks

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.MovieDao
import com.example.movieappkotlin.model.TmdbResponse
import com.example.movieapptask.data.MoviesRepository
import com.example.movieapptask.network.MovieAPI
import com.example.movieapptask.util.Connectivity

class MockMoviesRepositoryImpl (private val movieAPI: MovieAPI, private val movieDao: MovieDao, private val connectivity: Connectivity?,private val mockData: LiveData<PagedList<Movie>>):
    MoviesRepository {
    override suspend fun getMoviesOnline(pageNumber: Int): TmdbResponse? {
       return movieAPI.getPopularMovies("","")
    }

    override fun getMoviesOffline(): DataSource.Factory<Int, Movie> {
        return movieDao.getAll()
    }

    override suspend fun saveAllMovies(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

    override fun getMovies(): LiveData<PagedList<Movie>> {
       return mockData
    }

}