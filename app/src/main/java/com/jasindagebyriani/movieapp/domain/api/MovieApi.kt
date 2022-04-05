package com.jasindagebyriani.movieapp.domain.api

import com.jasindagebyriani.movieapp.domain.api.response.PopularMoviesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovie(): Observable<PopularMoviesResponse>
}