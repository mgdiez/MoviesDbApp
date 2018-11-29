package com.marcgdiez.moviedbapp.di

import android.content.Context
import com.marcgdiez.moviedbapp.MoviesDbApp
import com.marcgdiez.moviedbapp.view.di.MoviesComponent
import dagger.Module
import dagger.Provides

@Module(
    subcomponents = [
        (MoviesComponent::class)
    ]
)
class AppModule {

    @Provides
    fun context(application: MoviesDbApp): Context = application.applicationContext

}