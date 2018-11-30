package com.marcgdiez.moviedbapp.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import android.widget.Toast
import com.marcgdiez.moviedbapp.Navigator
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.loadWithTranstion
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_detail_view.*
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    companion object {
        fun getCallingIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_MOVIE, movie)
            return intent
        }

        const val ARG_MOVIE = "ARG_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        AndroidInjection.inject(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportPostponeEnterTransition()

        initRecyclerView()
        initToolbar()
        getExtras()
    }

    private fun initRecyclerView() {
        with(recyclerView) {
            adapter = MovieRecommendedAdapter(Navigator.NavigatorImpl(this@MovieDetailActivity))
            layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getExtras() {
        val extras = intent.extras
        extras?.apply {
            val movie = getParcelable(ARG_MOVIE) as Movie
            val imageTransitionName = movie.id.toString()
            imageView.transitionName = imageTransitionName
            imageView.loadWithTranstion(movie.backdropPath, this@MovieDetailActivity)
            presenter.onViewReady(movie)
        }
    }

    override fun showTitleShow(movie: Movie) {
        toolbar.title = movie.name
    }

    override fun showMovieDetails(movie: Movie) {
        titleShow.text = movie.name
        voteAvg.text = movie.voteAverage.toString()
        date.text = movie.firstAirDate
        overview.text = movie.overview
        voteNum.text = movie.nVotes.toString()
        popularity.text = movie.popularity.toString()
    }

    override fun hideRecommendations() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun showRecommendations(movies: List<Movie>) {
        val moviesAdapter = recyclerView.adapter as? MovieRecommendedAdapter
        moviesAdapter?.setMovies(movies)
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24px)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}