package com.marcgdiez.moviedbapp.config

import androidx.test.platform.app.InstrumentationRegistry
import com.marcgdiez.moviedbapp.MoviesDbApp
import com.marcgdiez.moviedbapp.asApp
import com.marcgdiez.moviedbapp.di.AppComponent
import com.marcgdiez.moviedbapp.di.AppModule
import com.marcgdiez.moviedbapp.di.NetworkModule
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.DaggerMockRule

fun getMoviesDaggerRule(): DaggerMockRule<AppComponent> =
    DaggerMock.rule(AppModule(), NetworkModule()) {
        set { component -> app.updateComponent(component) }
        customizeBuilder<AppComponent.Builder> { it.application(app) }
    }

val app: MoviesDbApp = InstrumentationRegistry.getInstrumentation().targetContext.asApp()
