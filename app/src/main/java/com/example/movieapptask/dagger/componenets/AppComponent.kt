package com.example.movieapptask.dagger.componenets

import com.example.movieapptask.dagger.modules.AppModule
import com.example.movieapptask.dagger.modules.DataModule
import com.example.movieapptask.dagger.modules.ServiceModule
import com.example.movieapptask.dagger.scopes.ApplicationScope
import com.example.movieapptask.dagger.viewmodelfactory.ViewModelModule
import com.example.movieapptask.ui.main.MainFragment
import dagger.Component


@ApplicationScope
@Component(modules = [AppModule::class, ServiceModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent{
    fun inject(mainFragment: MainFragment)
}