package com.marcgdiez.moviedbapp.di

import android.app.Activity
import com.marcgdiez.moviedbapp.view.MoviesFeedActivity
import com.marcgdiez.moviedbapp.view.di.MoviesComponent
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
    abstract fun bindPlacesActivityInjectorFactory(builder: MoviesComponent.Builder): AndroidInjector.Factory<out Activity>

}