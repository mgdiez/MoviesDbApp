package com.marcgdiez.moviedbapp.view.detail

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.config.getMoviesDaggerRule
import com.marcgdiez.moviedbapp.di.AppComponent
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import com.schibsted.spain.barista.rule.BaristaRule
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {

    @get:Rule
    val activityRule = BaristaRule.create(MovieDetailActivity::class.java)

    @get:Rule
    val daggerRule: DaggerMockRule<AppComponent> = getMoviesDaggerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun show_movie_details() {

        activityRule.launchActivity(provideIntent())

        assertDisplayed(R.id.titleShow, "Mr. Robot")
        assertDisplayed(R.id.voteAvg, "9.5")
        assertDisplayed(R.id.date, "10-12-1992")
        assertDisplayed(R.id.overview, "Overview")
        assertDisplayed(R.id.voteNum, "1535")
        assertDisplayed(R.id.popularity, "16.0")
    }

    @Test
    fun hide_recommendations_invalid_id() {
        activityRule.launchActivity(provideIntent())

        assertNotDisplayed(R.id.recyclerView)
    }

    private fun provideIntent(): Intent {
        val i = Intent()
        i.putExtra(MovieDetailActivity.ARG_MOVIE, provideMovie())
        return i
    }

    private fun provideMovie(): Movie = Movie(
        "someUrl",
        "10-12-1992", 1111, "Mr. Robot", "Overview", 16.0, "someUrl", 9.5, 1535
    )

}