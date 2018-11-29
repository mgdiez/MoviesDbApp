package com.marcgdiez.moviedbapp.data.dto

data class GetMoviesResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)