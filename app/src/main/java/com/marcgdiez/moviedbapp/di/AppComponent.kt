package com.marcgdiez.moviedbapp.di

import com.marcgdiez.moviedbapp.MoviesDbApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (NetworkModule::class),
        (BuildersModule::class)
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesDbApp): Builder

        fun appModule(appModule: AppModule): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: MoviesDbApp)
}