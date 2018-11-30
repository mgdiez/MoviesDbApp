package com.marcgdiez.moviedbapp.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.load
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies_feed.*
import javax.inject.Inject


class MoviesFeedActivity : AppCompatActivity(), MoviesFeedContract.View {

    @Inject
    lateinit var presenter: MoviesFeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_feed)
        initStatusBar()
        initViews()
        AndroidInjection.inject(this)
        presenter.onViewReady()
    }

    private fun initViews() {
        with(recyclerView) {
            adapter = MovieAdapter{ presenter.onMovieClick(it) }
            val linearLayoutManager = LinearLayoutManager(this@MoviesFeedActivity)
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1)
                        presenter.onBottomReached()
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
        }
    }

    private fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun showMovies(movies: List<Movie>) {
        val moviesAdapter = recyclerView.adapter as? MovieAdapter
        moviesAdapter?.setMovies(movies)
    }

    override fun showHeaderMovie(movie: Movie) {
        headerImageView.load(movie.imageUrl)
        titleHeader.text = movie.name
        ratingHeader.text = movie.voteAverage.toString()
    }

    override fun addMovies(movies: List<Movie>) {
        val moviesAdapter = recyclerView.adapter as? MovieAdapter
        moviesAdapter?.addMovies(movies)
    }

    override fun showError() {
    }
}
