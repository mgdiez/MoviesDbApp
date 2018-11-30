package com.marcgdiez.moviedbapp.view

import com.marcgdiez.moviedbapp.domain.GetMoviesUseCase
import com.marcgdiez.moviedbapp.domain.bo.Movie

class MoviesFeedPresenter(
    private val view: MoviesFeedContract.View
    , private val getMoviesUseCase: GetMoviesUseCase
) : MoviesFeedContract.Presenter {

    override fun onMovieClick(it: Movie) {

    }

    override fun onViewReady() {
        getMoviesUseCase.execute(1, { view.showMovies(it.moviesList) }, { view.showError() })
    }
}