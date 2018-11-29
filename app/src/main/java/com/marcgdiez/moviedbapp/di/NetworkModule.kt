package com.marcgdiez.moviedbapp.di

import com.marcgdiez.moviedbapp.data.MoviesRepositoryImpl
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun googlePlacesRepository(): MoviesRepository = MoviesRepositoryImpl()
}