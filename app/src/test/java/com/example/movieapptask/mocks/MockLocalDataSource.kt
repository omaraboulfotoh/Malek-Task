package com.example.movieapptask.mocks

import androidx.paging.DataSource
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.MovieDao
import org.mockito.Mockito.mock

class MockLocalDataSource(private var mockMovies: MutableList<Movie>):MovieDao {
    override fun getAll(): DataSource.Factory<Int, Movie> {
        return mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
    }

    override suspend fun insert(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(movies: List<Movie>) {
        mockMovies.addAll(movies)
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun update(movie: Movie) {
        TODO("Not yet implemented")
    }
}