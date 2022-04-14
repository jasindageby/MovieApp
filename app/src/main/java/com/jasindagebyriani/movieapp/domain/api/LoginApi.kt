package com.jasindagebyriani.movieapp.domain.api

import com.jasindagebyriani.movieapp.domain.api.response.LoginRequest
import com.jasindagebyriani.movieapp.domain.api.response.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/v1/login")
    fun onLogin(request: LoginRequest): Single<LoginResponse>
}