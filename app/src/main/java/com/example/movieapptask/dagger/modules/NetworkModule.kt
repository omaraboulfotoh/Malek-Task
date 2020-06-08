package com.example.movieapptask.dagger.modules

import com.example.movieapptask.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class NetworkModule {
    companion object{
         const val BASE_URL = "http://api.themoviedb.org/3/"
    }

    @Provides
    @ApplicationScope
    @Named(BASE_URL)
    fun provideBaseUrlString() = BASE_URL

    @Provides
    @ApplicationScope
    fun provideClient() = OkHttpClient()

    @Provides
    @ApplicationScope
    fun provideMosheConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

}