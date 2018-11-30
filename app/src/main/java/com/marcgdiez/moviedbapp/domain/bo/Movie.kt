package com.marcgdiez.moviedbapp.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val name: String,
    val country: List<String>,
    val language: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val imageUrl: String,
    val voteAverage: Double,
    val nVotes: Int
) : Parcelable