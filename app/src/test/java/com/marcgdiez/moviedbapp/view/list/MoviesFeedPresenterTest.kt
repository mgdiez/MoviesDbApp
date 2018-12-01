package com.marcgdiez.moviedbapp.view.list

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.domain.usecase.GetMoviesUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MoviesFeedPresenterTest {

    private var mockView: MoviesFeedContract.View = mock()
    private var mockGetMoviesUseCase: GetMoviesUseCase = mock()

    private val presenter by lazy {
        MoviesFeedPresenter(
            mockView,
            mockGetMoviesUseCase
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should show loading when fetching movies`() {
        presenter.onViewReady()

        verify(mockView).showLoading()
    }

    @Test
    fun `should show request data on view ready`() {
        presenter.onViewReady()

        verify(mockGetMoviesUseCase).execute(any(), any(), any())
    }

    @Test
    fun `should request more data on bottom reached if possible`() {
        presenter.maxPages = 2
        presenter.page = 1

        presenter.onBottomReached()

        verify(mockGetMoviesUseCase).execute(any(), any(), any())
    }

    @Test
    fun `should not request more data on bottom reached if possible`() {
        presenter.maxPages = 10
        presenter.page = 10

        presenter.onBottomReached()

        verifyZeroInteractions(mockGetMoviesUseCase)
    }

    @Test
    fun `should request data on retry click`() {
        presenter.onRetryClick()

        verify(mockGetMoviesUseCase).execute(any(), any(), any())
    }

    @Test
    fun `should hide error view on retry click`() {
        presenter.onRetryClick()

        verify(mockView).hideError()
    }

    @Test
    fun `should show loading on retry click`() {
        presenter.onRetryClick()

        verify(mockView).showLoading()
    }

    @Test
    fun `should clear usecase onstop`() {
        presenter.onStop()

        verify(mockGetMoviesUseCase).clear()
    }

    @Test
    fun `should show error view given error response`() {
        presenter.handleError(Throwable())

        verify(mockView).showError()
    }

    @Test
    fun `should hide loading when first response is empty`() {
        presenter.handleSuccess(givenEmptyResponse())

        verify(mockView).hideLoading()
    }

    @Test
    fun `should show movies when first response has data`() {
        val getMoviesResponse = givenSuccessResponse()
        presenter.handleSuccess(getMoviesResponse)

        verify(mockView).showMovies(getMoviesResponse.moviesList)
        verify(mockView).hideLoading()
    }

    @Test
    fun `should add movies when nonfirst response has data`() {
        val getMoviesResponse = givenSuccessResponse()
        presenter.page = 2

        presenter.handleSuccess(getMoviesResponse)

        verify(mockView).addMovies(getMoviesResponse.moviesList)
    }


    private fun givenSuccessResponse(): GetMoviesResponse =
        GetMoviesResponse(
            listOf(
                Movie(
                    "",
                    "", 1, "", "", 0.0, "", 0.0, 1
                )
            ), 1
        )

    private fun givenEmptyResponse(): GetMoviesResponse = GetMoviesResponse(emptyList(), 1)
}