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
                        generateBackdropImageUrl(it.backdrop_path), it.first_air_date,
                        it.id, it.name, it.overview, it.popularity, generatePosterImageUrl(it.poster_path),
                        it.vote_average, it.vote_count
                )
            }

    private fun generatePosterImageUrl(url: String) =
            NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_POSTER_IMAGE_SIZE + url

    private fun generateBackdropImageUrl(url: String) =
            NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_BACKDROP_IMAGE_SIZE + url
}
