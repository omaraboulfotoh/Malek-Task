package com.example.movieapptask.dagger.modules

import android.app.Application
import com.example.movieapptask.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideContext(): Application = app
}