package com.marcgdiez.moviedbapp.view.detail

import com.marcgdiez.moviedbapp.domain.bo.Movie

interface MovieDetailContract {
    interface View {
        fun showMovieDetails(movie: Movie)
        fun showTitleShow(movie: Movie)
        fun hideRecommendations()
        fun showRecommendations(moviesList: List<Movie>)
    }

    interface Presenter {
        fun onViewReady(movie: Movie)
        fun onStop()
    }
}