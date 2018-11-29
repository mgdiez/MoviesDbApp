package com.marcgdiez.moviedbapp.view.di

import android.app.Activity
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.view.MoviesFeedActivity
import com.marcgdiez.moviedbapp.view.MoviesFeedContract
import com.marcgdiez.moviedbapp.view.MoviesFeedPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MoviesModule {
    @Binds
    @PerActivity
    internal abstract fun provideView(activity: MoviesFeedActivity): MoviesFeedContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: MoviesFeedActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: MoviesFeedContract.View
        ): MoviesFeedContract.Presenter =
            MoviesFeedPresenter(
                view
            )
    }
}