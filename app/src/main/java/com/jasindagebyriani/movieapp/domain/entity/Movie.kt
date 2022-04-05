package com.jasindagebyriani.movieapp.domain.entity

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    private val posterPath: String,
    private val backdropPath: String,
    val releaseDate: String,
    val originalLanguage: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genre: List<String>
) {
    fun getBackdropPath() = PREFIX_IMAGE + backdropPath

    fun getPosterPath() = PREFIX_IMAGE + posterPath

    companion object {
        private const val PREFIX_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}
