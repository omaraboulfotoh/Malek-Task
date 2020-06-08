package com.example.movieapptask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieapptask.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository): ViewModel() {

    val movies: LiveData<PagedList<Movie>> = moviesRepository.getMovies()
}