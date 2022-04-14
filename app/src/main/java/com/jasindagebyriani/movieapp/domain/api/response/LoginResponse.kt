package com.jasindagebyriani.movieapp.domain.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val id: Long,
    val username: String,
    val avatar: String
)
