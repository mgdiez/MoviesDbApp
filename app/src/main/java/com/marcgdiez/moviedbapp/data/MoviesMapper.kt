package com.marcgdiez.moviedbapp.data

import com.marcgdiez.moviedbapp.data.dto.GetMoviesResponseDto
import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import com.marcgdiez.moviedbapp.domain.bo.Movie

class MoviesMapper {
    fun map(getMoviesResponseDto: GetMoviesResponseDto): GetMoviesResponse =
        GetMoviesResponse(
            moviesDtoToBo(getMoviesResponseDto),
            getMoviesResponseDto.total_pages
        )

    private fun moviesDtoToBo(getMoviesResponse: GetMoviesResponseDto): List<Movie> =
        getMoviesResponse.results.map {
            Movie(
                generateImageUrl(it.backdrop_path), it.first_air_date,
                it.genre_ids, it.id, it.name, it.origin_country, it.original_language, it.original_name, it.overview,
                it.popularity, generateImageUrl(it.poster_path), it.vote_average, it.vote_count
            )
        }

    private fun generateImageUrl(url: String) = NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_IMAGE_SIZE + url
}
