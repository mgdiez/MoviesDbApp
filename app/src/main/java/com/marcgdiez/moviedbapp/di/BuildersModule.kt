package com.marcgdiez.moviedbapp.di

import android.app.Activity
import com.marcgdiez.moviedbapp.view.detail.MovieDetailActivity
import com.marcgdiez.moviedbapp.view.detail.di.MoviesDetailComponent
import com.marcgdiez.moviedbapp.view.list.MoviesFeedActivity
import com.marcgdiez.moviedbapp.view.list.di.MoviesComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(MoviesFeedActivity::class)
    abstract fun bindMoviesFeedActivityInjectorFactory(builder: MoviesComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(MovieDetailActivity::class)
    abstract fun bindMoviesDetailActivityInjectorFactory(builder: MoviesDetailComponent.Builder): AndroidInjector.Factory<out Activity>

}