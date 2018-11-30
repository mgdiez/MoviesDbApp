package com.marcgdiez.moviedbapp.view.detail

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.domain.usecase.GetRecommendationsUseCase

class MovieDetailPresenter(private val view: MovieDetailContract.View,
                           private val getRecommendationsUseCase: GetRecommendationsUseCase) : MovieDetailContract.Presenter {

    override fun onViewReady(movie: Movie) {
        view.showTitleShow(movie)
        view.showMovieDetails(movie)

        getRecommendationsUseCase.execute(movie.id, ::handleSuccess, ::handleError)
    }

    private fun handleSuccess(getMoviesResponse: GetMoviesResponse) {
        view.showRecommendations(getMoviesResponse.moviesList)
    }

    private fun handleError(throwable: Throwable) {
        view.hideRecommendations()
    }

    override fun onStop() {
        getRecommendationsUseCase.clear()
    }
}