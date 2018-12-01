package com.marcgdiez.moviedbapp.view.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcgdiez.moviedbapp.Navigator
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.hide
import com.marcgdiez.moviedbapp.extensions.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies_feed.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject


class MoviesFeedActivity : AppCompatActivity(), MoviesFeedContract.View {

    @Inject
    lateinit var presenter: MoviesFeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_feed)
        initViews()
        AndroidInjection.inject(this)
        presenter.onViewReady()
    }

    private fun initViews() {
        with(recyclerView) {
            adapter = MovieAdapter(Navigator.NavigatorImpl(this@MoviesFeedActivity))
            val linearLayoutManager = LinearLayoutManager(this@MoviesFeedActivity)
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1)
                        presenter.onBottomReached()
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
            setHasFixedSize(true)
        }

        retryBtn.setOnClickListener { presenter.onRetryClick() }
    }

    override fun showMovies(movies: List<Movie>) {
        recyclerView.show()
        errorLayout.hide()

        val moviesAdapter = recyclerView.adapter as? MovieAdapter
        moviesAdapter?.setMovies(movies)
    }

    override fun addMovies(movies: List<Movie>) {
        val moviesAdapter = recyclerView.adapter as? MovieAdapter
        moviesAdapter?.addMovies(movies)
    }

    override fun showError() {
        errorLayout.show()
        progressView.hide()
        recyclerView.hide()
    }

    override fun hideError() = errorLayout.hide()

    override fun showLoading() = progressView.show()

    override fun hideLoading() = progressView.hide()

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
