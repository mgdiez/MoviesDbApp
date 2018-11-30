package com.marcgdiez.moviedbapp.view.detail.di

import android.app.Activity
import com.marcgdiez.moviedbapp.di.PerActivity
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.domain.usecase.GetRecommendationsUseCase
import com.marcgdiez.moviedbapp.view.detail.MovieDetailActivity
import com.marcgdiez.moviedbapp.view.detail.MovieDetailContract
import com.marcgdiez.moviedbapp.view.detail.MovieDetailPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

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
        internal fun provideGetRecommendationsUseCase(
                moviesRepository: MoviesRepository,
                @Named("observeOn") observeOn: Scheduler,
                @Named("subscribeOn") subscribeOn: Scheduler
        ): GetRecommendationsUseCase = GetRecommendationsUseCase(moviesRepository, observeOn, subscribeOn)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
                view: MovieDetailContract.View,
                getRecommendationsUseCase: GetRecommendationsUseCase
        ): MovieDetailContract.Presenter =
                MovieDetailPresenter(
                        view,
                        getRecommendationsUseCase
                )
    }
}