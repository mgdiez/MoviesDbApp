package com.marcgdiez.moviedbapp.domain.bo

data class GetMoviesResponse(
    val moviesList: List<Movie>,
    val totalPages: Int
)