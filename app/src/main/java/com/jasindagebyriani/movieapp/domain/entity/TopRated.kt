package com.jasindagebyriani.movieapp.domain.entity

data class TopRated(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val originalLanguage: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genreIds: List<Long>
)
