package com.marcgdiez.moviedbapp.view.di

import android.app.Activity
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.domain.GetMoviesUseCase
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.view.MoviesFeedActivity
import com.marcgdiez.moviedbapp.view.MoviesFeedContract
import com.marcgdiez.moviedbapp.view.MoviesFeedPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

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
        internal fun provideGetMoviesUseCase(
            moviesRepository: MoviesRepository,
            @Named("observeOn") observeOn: Scheduler,
            @Named("subscribeOn") subscribeOn: Scheduler
        ): GetMoviesUseCase = GetMoviesUseCase(moviesRepository, observeOn, subscribeOn)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: MoviesFeedContract.View,
            getMoviesUseCase: GetMoviesUseCase
        ): MoviesFeedContract.Presenter =
            MoviesFeedPresenter(
                view,
                getMoviesUseCase
            )
    }
}