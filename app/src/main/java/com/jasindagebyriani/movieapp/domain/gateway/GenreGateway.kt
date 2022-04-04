package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.GenreApi
import com.jasindagebyriani.movieapp.domain.api.response.GenreResponse
import com.jasindagebyriani.movieapp.domain.entity.Genre
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface GenreGateway {
    fun getGenre(): Observable<List<Genre>>
}

class GenreGatewayImpl @Inject constructor(
    private val genreApi: GenreApi
) : GenreGateway {
    override fun getGenre(): Observable<List<Genre>> {
        return genreApi.getGenre()
            .map { it.genres.mapToGenres() }
    }

    private fun List<GenreResponse>.mapToGenres(): List<Genre> {
        return map { response ->
            with(response) {
                Genre(id, name)
            }
        }
    }
}