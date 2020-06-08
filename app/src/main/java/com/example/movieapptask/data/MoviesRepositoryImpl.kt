package com.example.movieapptask.data

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.MovieDao
import com.example.movieappkotlin.model.TmdbResponse
import com.example.movieapptask.network.MovieAPI
import com.example.movieapptask.ui.main.MoviesBoundaryCallback
import com.example.movieapptask.util.Connectivity
import com.example.movieapptask.util.api_key
import kotlinx.coroutines.Dispatchers

class MoviesRepositoryImpl (private val movieAPI: MovieAPI, private val movieDao: MovieDao,private val connectivity: Connectivity?,private val sharedPreferences: SharedPreferences): MoviesRepository {
    override suspend fun getMoviesOnline(pageNumber: Int): TmdbResponse? {
        connectivity?.let {
            return if(connectivity.checkForConnectivity()){
                movieAPI.getPopularMovies(api_key, pageNumber.toString())
            }else{
                null
            }
        }
       return null
    }

    override fun getMoviesOffline(): DataSource.Factory<Int, Movie> {
        return movieDao.getAll()
    }

    override suspend fun saveAllMovies(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

    override fun getMovies(): LiveData<PagedList<Movie>>{
        val dataSourceFactory = getMoviesOffline()
        val boundaryCallback =
            MoviesBoundaryCallback(
                movieAPI = movieAPI,
                movieDao = movieDao,
                connectivity = connectivity,
                dispatcher = Dispatchers.IO,
                sharedPreferences = sharedPreferences
            )
        val data = LivePagedListBuilder(dataSourceFactory, 20)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return  data
    }

}