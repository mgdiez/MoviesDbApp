package com.marcgdiez.moviedbapp.view

import com.marcgdiez.moviedbapp.domain.bo.Movie

interface MoviesFeedContract {
    interface View {
        fun showError()
        fun showMovies(movies: List<Movie>)
        fun showHeaderMovie(movie: Movie)
    }

    interface Presenter {
        fun onViewReady()
        fun onMovieClick(it: Movie)
    }
}