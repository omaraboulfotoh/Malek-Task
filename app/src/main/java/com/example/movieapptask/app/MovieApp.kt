package com.example.movieapptask.app

import android.app.Application
import com.example.movieapptask.dagger.componenets.AppComponent
import com.example.movieapptask.dagger.componenets.DaggerAppComponent
import com.example.movieapptask.dagger.modules.AppModule

class MovieApp: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}