package com.marcgdiez.moviedbapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marcgdiez.moviedbapp.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MoviesFeedActivity : AppCompatActivity(), MoviesFeedContract.View {

    @Inject
    lateinit var presenter: MoviesFeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_feed)

        AndroidInjection.inject(this)

        presenter.onViewReady()
    }
}
