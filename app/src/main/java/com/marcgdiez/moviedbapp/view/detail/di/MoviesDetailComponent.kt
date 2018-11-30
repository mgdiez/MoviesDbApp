package com.marcgdiez.moviedbapp.view.detail.di

import com.marcgdiez.moviedbapp.di.ActivityModule
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.view.detail.MovieDetailActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [(ActivityModule::class), (MoviesDetailModule::class)])
interface MoviesDetailComponent : AndroidInjector<MovieDetailActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MovieDetailActivity>()
}