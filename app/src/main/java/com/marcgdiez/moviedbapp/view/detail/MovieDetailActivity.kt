package com.marcgdiez.moviedbapp.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.loadWithTranstion
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {

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
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        supportPostponeEnterTransition()

        val extras = intent.extras

        extras?.apply {
            val movie = getParcelable(ARG_MOVIE) as Movie
            val imageTransitionName = movie.id.toString()
            imageView.transitionName = imageTransitionName
            imageView.loadWithTranstion(movie.backdropPath, this@MovieDetailActivity)
            toolbar.title = movie.name
        }

        initToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24px)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}