package com.example.movieapptask.mocks

import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.TmdbResponse
import com.example.movieapptask.network.MovieAPI

class MockRemoteDataSource(private var movies: List<Movie>): MovieAPI {
    override suspend fun getPopularMovies(apiKey: String, page: String): TmdbResponse? {
        return TmdbResponse().apply {
            results = movies
        }
    }

}