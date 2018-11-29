package com.marcgdiez.moviedbapp.di

import android.content.Context
import com.marcgdiez.moviedbapp.MoviesDbApp
import com.marcgdiez.moviedbapp.view.di.MoviesComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module(
    subcomponents = [
        (MoviesComponent::class)
    ]
)
class AppModule {

    @Provides
    fun context(application: MoviesDbApp): Context = application.applicationContext

    @Provides
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()
}