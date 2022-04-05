package com.jasindagebyriani.movieapp.domain.api

import com.jasindagebyriani.movieapp.domain.api.response.GenreResponses
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface GenreApi {
    @GET("genre/movie/list")
    fun getGenre(): Observable<GenreResponses>
}