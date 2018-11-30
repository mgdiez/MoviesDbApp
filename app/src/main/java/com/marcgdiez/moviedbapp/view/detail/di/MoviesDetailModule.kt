package com.marcgdiez.moviedbapp.view.detail.di

import android.app.Activity
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.view.detail.MovieDetailActivity
import com.marcgdiez.moviedbapp.view.detail.MovieDetailContract
import com.marcgdiez.moviedbapp.view.detail.MovieDetailPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MoviesDetailModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: MovieDetailActivity): MovieDetailContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: MovieDetailActivity): Activity

    @Module
    companion object {

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
                view: MovieDetailContract.View
        ): MovieDetailContract.Presenter =
                MovieDetailPresenter(
                        view
                )
    }
}