package com.marcgdiez.moviedbapp.data

import com.marcgdiez.moviedbapp.domain.Movie
import com.marcgdiez.moviedbapp.domain.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl : MoviesRepository {
    override fun getMovies(page: Int): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}