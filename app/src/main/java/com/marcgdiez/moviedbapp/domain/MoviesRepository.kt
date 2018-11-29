package com.marcgdiez.moviedbapp.domain

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(page: Int): Single<GetMoviesResponse>
}