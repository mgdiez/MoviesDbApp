package com.marcgdiez.moviedbapp

import android.app.Activity

interface Navigator {

    fun navigateToDetail()

    class NavigatorImpl(
        private val activity: Activity
    ) : Navigator {
        override fun navigateToDetail() {

        }

    }
}