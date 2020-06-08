package com.example.movieapptask.dagger.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.movieappkotlin.model.LocalDatabase
import com.example.movieappkotlin.model.MovieDao
import com.example.movieapptask.data.MoviesRepository
import com.example.movieapptask.data.MoviesRepositoryImpl
import com.example.movieapptask.network.MovieAPI
import com.example.movieapptask.util.Connectivity
import com.example.movieapptask.dagger.scopes.ApplicationScope
import com.example.movieapptask.util.sharedPreferencesName
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, AppModule::class])
class DataModule {

    @Provides
    @ApplicationScope
    fun provideMoviesRepository(movieAPI: MovieAPI, movieDao: MovieDao, connectivity: Connectivity, sharedPreferences: SharedPreferences): MoviesRepository
            = MoviesRepositoryImpl(movieAPI, movieDao, connectivity, sharedPreferences)

    @Provides
    @ApplicationScope
    fun provideRoomDB(application: Application) = Room.databaseBuilder(application, LocalDatabase::class.java,"movie_database").fallbackToDestructiveMigration().build()

    @Provides
    @ApplicationScope
    fun provideLocalDBDao(localDB: LocalDatabase) = localDB.movieDao()

    @Provides
    @ApplicationScope
    fun providesConnectivity(app:Application) = Connectivity(app)

    @Provides
    @ApplicationScope
    fun provideSharedPrefrences(application: Application): SharedPreferences = application.applicationContext.getSharedPreferences(sharedPreferencesName,
        Context.MODE_PRIVATE)


}