package com.marcgdiez.moviedbapp.view.list

import com.marcgdiez.moviedbapp.domain.GetMoviesUseCase
import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie

class MoviesFeedPresenter(
        private val view: MoviesFeedContract.View
        , private val getMoviesUseCase: GetMoviesUseCase
) : MoviesFeedContract.Presenter {

    private var page: Int = 1
    private var maxPages: Int = 1

    override fun onMovieClick(it: Movie) {

    }

    override fun onViewReady() {
        requestData()
    }

    private fun requestData() {
        getMoviesUseCase.execute(page, ::handleSuccess, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        if (page == 1) {
            view.showError()
        }
    }

    private fun handleSuccess(getMoviesResponse: GetMoviesResponse) {
        val movies = getMoviesResponse.moviesList
        maxPages = getMoviesResponse.totalPages
        if (movies.isNotEmpty()) {
            when (page) {
                1 -> {
                    view.showHeaderMovie(movies[0])
                    view.showMovies(movies.subList(1, movies.size))
                }
                else -> view.addMovies(movies)
            }
        }
    }

    override fun onBottomReached() {
        page++
        if (page < maxPages) requestData()
    }
}