package com.jasindagebyriani.movieapp.domain.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    val results: List<PopularResponse>
)

@JsonClass(generateAdapter = true)
data class PopularResponse(
    val id: Long,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Long,
    @Json(name = "genre_ids") val genreIds: List<Long>
)
