package com.jasindagebyriani.movieapp.domain.api

import com.jasindagebyriani.movieapp.domain.api.response.PopularMoviesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MovieApi {
    @GET("/movie/popular?api_key=0713f7d91f6c07ec3aac82539a20bed8")
    fun getPopularMovie(): Observable<PopularMoviesResponse>
}