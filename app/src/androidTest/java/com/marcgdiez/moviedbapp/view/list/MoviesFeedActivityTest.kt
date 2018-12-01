package com.marcgdiez.moviedbapp.view.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.config.getMoviesDaggerRule
import com.marcgdiez.moviedbapp.di.AppComponent
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import com.schibsted.spain.barista.rule.BaristaRule
import io.reactivex.Single
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Rule
import org.junit.Test

class MoviesFeedActivityTest {

    @get:Rule
    val activityRule = BaristaRule.create(MoviesFeedActivity::class.java)

    @get:Rule
    val daggerRule: DaggerMockRule<AppComponent> = getMoviesDaggerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val moviesRepository: MoviesRepository = mock()

    @Test
    fun test() {
        given { moviesRepository.getMovies(1) }.willReturn(
            Single.just(
                GetMoviesResponse(
                    listOf(provideMovie()),
                    1
                )
            )
        )

        activityRule.launchActivity()

        sleep(1000)
        assertRecyclerViewItemCount(R.id.recyclerView, 20)
    }

    private fun provideMovie(): Movie = Movie(
        "",
        "", 1, "", "", 0.0, "", 0.0, 1
    )
}