package com.marcgdiez.moviedbapp.domain

import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(page: Int) : Single<List<Movie>>
}