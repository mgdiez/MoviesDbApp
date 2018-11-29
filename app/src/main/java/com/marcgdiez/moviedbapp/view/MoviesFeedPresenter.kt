package com.marcgdiez.moviedbapp.view

import com.marcgdiez.moviedbapp.domain.GetMoviesUseCase

class MoviesFeedPresenter(
    private val view: MoviesFeedContract.View
    , private val getMoviesUseCase: GetMoviesUseCase
) : MoviesFeedContract.Presenter {

    override fun onViewReady() {

    }
}