package com.marcgdiez.moviedbapp.view.list

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.usecase.GetMoviesUseCase

class MoviesFeedPresenter(
    private val view: MoviesFeedContract.View
    , private val getMoviesUseCase: GetMoviesUseCase
) : MoviesFeedContract.Presenter {

    internal var page: Int = 1
    internal var maxPages: Int = 1

    override fun onViewReady() {
        view.showLoading()
        requestData()
    }

    private fun requestData() = getMoviesUseCase.execute(page, ::handleSuccess, ::handleError)

    internal fun handleError(throwable: Throwable) {
        if (page == 1) view.showError()
    }

    internal fun handleSuccess(getMoviesResponse: GetMoviesResponse) {
        val movies = getMoviesResponse.moviesList
        maxPages = getMoviesResponse.totalPages
        if (movies.isNotEmpty()) {
            when (page) {
                1 -> {
                    view.showMovies(movies)
                    view.hideLoading()
                }
                else -> view.addMovies(movies)
            }
        } else {
            view.hideLoading()
        }
    }

    override fun onBottomReached() {
        if (++page <= maxPages) requestData()
    }

    override fun onRetryClick() {
        view.hideError()
        view.showLoading()
        requestData()
    }

    override fun onStop() = getMoviesUseCase.clear()
}