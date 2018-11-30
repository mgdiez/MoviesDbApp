package com.marcgdiez.moviedbapp.view.list.di

import com.marcgdiez.moviedbapp.di.ActivityModule
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.view.list.MoviesFeedActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [(ActivityModule::class), (MoviesModule::class)])
interface MoviesComponent : AndroidInjector<MoviesFeedActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesFeedActivity>()
}