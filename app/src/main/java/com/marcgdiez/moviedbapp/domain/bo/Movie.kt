package com.marcgdiez.moviedbapp.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    val imageUrl: String,
    val voteAverage: Double,
    val nVotes: Int
) : Parcelable