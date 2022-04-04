package com.jasindagebyriani.movieapp.domain.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponses(
    val genres: List<GenreResponse>
)

@JsonClass(generateAdapter = true)
data class GenreResponse(
    val id: Long,
    val name: String
)
