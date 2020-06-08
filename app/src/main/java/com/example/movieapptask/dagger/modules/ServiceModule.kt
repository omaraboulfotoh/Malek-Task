package com.example.movieapptask.dagger.modules

import com.example.movieapptask.network.MovieAPI
import com.example.movieapptask.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module(includes = [NetworkModule::class])
class ServiceModule {
    @Provides
    @ApplicationScope
    fun provideMoviesApi(@Named(NetworkModule.BASE_URL) baseURL: String, client: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit
        .Builder()
        .baseUrl(baseURL)
        .client(client)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create(MovieAPI::class.java)


}