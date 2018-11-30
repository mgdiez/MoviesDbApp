package com.marcgdiez.moviedbapp.data

import java.util.*

object NetworkConfig {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "fab7d97ae19573a00a05dcea995abddf"
    val API_LAN: String
        get() = Locale.getDefault().language
    const val API_BASE_IMAGE = "https://image.tmdb.org/t/p/"
    const val API_POSTER_IMAGE_SIZE = "w500"
    const val API_BACKDROP_IMAGE_SIZE = "w1280"
}