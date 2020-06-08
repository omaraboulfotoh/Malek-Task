package com.example.movieapptask.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.movieappkotlin.model.Movie
import com.example.movieapptask.data.MoviesRepository
import com.example.movieapptask.mocks.MockLocalDataSource
import com.example.movieapptask.mocks.MockMoviesRepositoryImpl
import com.example.movieapptask.mocks.MockRemoteDataSource
import com.example.movieapptask.ui.main.MoviesBoundaryCallback
import com.example.movieapptask.utils.MainCoroutineRule
import com.example.movieapptask.utils.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MoviesRepositoryTest {
    val localMovies = mutableListOf(Movie("title1"), Movie("title2"))
    val remoteMovies = mutableListOf(Movie("title11"), Movie("title22"))
    private lateinit var mockLocalDB: MockLocalDataSource
    private lateinit var mockRemoteAPI: MockRemoteDataSource
    private lateinit var  boundaryCallback: MoviesBoundaryCallback
    private lateinit var moviesRepository: MoviesRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun initRepository() {
        val mockData = mockPagedList(localMovies)
        mockLocalDB = MockLocalDataSource(localMovies)
        mockRemoteAPI = MockRemoteDataSource(remoteMovies)
        boundaryCallback = MoviesBoundaryCallback(mockRemoteAPI, mockLocalDB,null, Dispatchers.Main)
        moviesRepository = MockMoviesRepositoryImpl(mockRemoteAPI, mockLocalDB, null, MutableLiveData(mockData))
    }

    @Test
    fun `getMovies Locally AssertNotNull`() = mainCoroutineRule.runBlockingTest {
        val movieResult =  moviesRepository.getMovies()
        val data = movieResult.getOrAwaitValue()
        assertEquals(data.size,2)
    }
}

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList
}

