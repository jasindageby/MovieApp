package com.jasindagebyriani.movieapp.view.viewobject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieViewObject(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val originalLanguage: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genre: List<String>
) : Parcelable
