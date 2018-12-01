package com.marcgdiez.moviedbapp.view.detail

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.domain.usecase.GetRecommendationsUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class MovieDetailPresenterTest {

    private var mockView: MovieDetailContract.View = mock()
    private var mockGetRecommendationsUseCase: GetRecommendationsUseCase = mock()

    private val presenter by lazy {
        MovieDetailPresenter(
            mockView,
            mockGetRecommendationsUseCase
        )
    }

    @Test
    fun `show movie details on view ready`() {
        val movie = givenMovie()

        presenter.onViewReady(movie)

        verify(mockView).showMovieDetails(movie)
    }

    @Test
    fun `should show title on view ready`() {
        val movie = givenMovie()

        presenter.onViewReady(movie)

        verify(mockView).showTitleShow(movie)
    }

    @Test
    fun `should show loading while getting recommendations`() {
        presenter.onViewReady(givenMovie())

        verify(mockView).showLoading()
    }

    @Test
    fun `should request recommendations when view is ready`() {
        presenter.onViewReady(givenMovie())

        verify(mockGetRecommendationsUseCase).execute(any(), any(), any())
    }

    @Test
    fun `hide loading when getting response`() {
        presenter.handleSuccess(givenMoviesResponse())

        verify(mockView).hideLoading()
    }

    @Test
    fun `show recommendations when successful response`() {
        val givenMoviesResponse = givenMoviesResponse()
        presenter.handleSuccess(givenMoviesResponse)

        verify(mockView).showRecommendations(givenMoviesResponse.moviesList)
    }

    @Test
    fun `hide recommendations when empty response`() {
        presenter.handleSuccess(givenEmptyResponse())

        verify(mockView).hideRecommendations()
    }

    @Test
    fun `hide recommendations when error response`() {
        presenter.handleError(Throwable())

        verify(mockView).hideRecommendations()
    }

    @Test
    fun `hide loading when error response`() {
        presenter.handleError(Throwable())

        verify(mockView).hideLoading()
    }

    @Test
    fun `clear usecase onstop`() {
        presenter.onStop()

        verify(mockGetRecommendationsUseCase).clear()
    }

    private fun givenEmptyResponse(): GetMoviesResponse = GetMoviesResponse(emptyList(), 1)

    private fun givenMoviesResponse(): GetMoviesResponse =
        GetMoviesResponse(
            listOf(
                givenMovie()
            ), 1
        )

    private fun givenMovie(): Movie = Movie(
        "",
        "", 1, "", "", 0.0, "", 0.0, 1
    )

}