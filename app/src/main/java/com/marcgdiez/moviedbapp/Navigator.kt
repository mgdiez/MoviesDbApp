package com.marcgdiez.moviedbapp

import android.app.Activity
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.view.detail.MovieDetailActivity

interface Navigator {

    fun navigateToDetail(movie: Movie, imageView: ImageView)

    class NavigatorImpl(
        private val activity: Activity
    ) : Navigator {
        override fun navigateToDetail(movie: Movie, imageView: ImageView) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                ViewCompat.getTransitionName(imageView)!!
            )
            activity.startActivity(MovieDetailActivity.getCallingIntent(activity, movie), options.toBundle())
        }

    }
}