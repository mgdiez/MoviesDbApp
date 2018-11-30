package com.marcgdiez.moviedbapp.view.list

import com.marcgdiez.moviedbapp.domain.bo.Movie

interface MoviesFeedContract {
    interface View {
        fun showError()
        fun showMovies(movies: List<Movie>)
        fun addMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun onViewReady()
        fun onBottomReached()
    }
}