package com.marcgdiez.moviedbapp.view.detail

import com.marcgdiez.moviedbapp.domain.bo.Movie

class MovieDetailPresenter(private val view: MovieDetailContract.View) : MovieDetailContract.Presenter {

    override fun onViewReady(movie: Movie) {
        view.showTitleShow(movie)
        view.showMovieDetails(movie)
    }

}