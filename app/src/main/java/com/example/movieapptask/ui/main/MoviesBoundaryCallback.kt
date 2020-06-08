package com.example.movieapptask.ui.main

import android.content.SharedPreferences
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.MovieDao
import com.example.movieappkotlin.model.TmdbResponse
import com.example.movieapptask.network.MovieAPI
import com.example.movieapptask.util.Connectivity
import com.example.movieapptask.util.api_key
import com.example.movieapptask.util.sharedPreferencesName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesBoundaryCallback(
    private val movieAPI: MovieAPI,
    private val movieDao: MovieDao,
    private val connectivity: Connectivity?,
    private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: SharedPreferences
) : PagedList.BoundaryCallback<Movie>() {

    private var lastRequestedPage = sharedPreferences.getString(sharedPreferencesName, "1")!!.toInt()
    private fun requestAndSaveMovies() {
        connectivity?.let {
            if (connectivity.checkForConnectivity()){
                 CoroutineScope(dispatcher).launch {
                    try {
                        val movies: TmdbResponse? = movieAPI.getPopularMovies(api_key, lastRequestedPage.toString())
                        movies?.results?.let {
                            movieDao.insertAll(it)
                            lastRequestedPage++
                            sharedPreferences.edit().putString(sharedPreferencesName, lastRequestedPage.toString()).commit()
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }

        }
    }
    override fun onZeroItemsLoaded() {
        requestAndSaveMovies()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        requestAndSaveMovies()
    }
}