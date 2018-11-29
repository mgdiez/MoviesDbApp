package com.marcgdiez.moviedbapp.di

import android.app.Activity
import com.marcgdiez.moviedbapp.Navigator
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideNavigator(activity: Activity): Navigator = Navigator.NavigatorImpl(activity)
    }
}