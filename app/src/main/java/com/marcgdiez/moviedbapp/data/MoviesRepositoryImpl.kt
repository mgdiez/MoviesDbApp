package com.marcgdiez.moviedbapp.data

import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import io.reactivex.Single

class MoviesRepositoryImpl(private val moviesApi: MoviesApi, private val moviesMapper: MoviesMapper) :
    MoviesRepository {
    override fun getMovies(page: Int): Single<GetMoviesResponse> =
        moviesApi.getMoviesList(NetworkConfig.API_KEY, NetworkConfig.API_LAN, page).map { moviesMapper.map(it) }
}