package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.MovieApi
import com.jasindagebyriani.movieapp.domain.api.response.PopularResponse
import com.jasindagebyriani.movieapp.domain.entity.Popular
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface PopularGateway {
    fun getPopularMovies(): Observable<List<Popular>>
}

class PopularGatewayImpl @Inject constructor(
    private val movieApi: MovieApi
) : PopularGateway {
    override fun getPopularMovies(): Observable<List<Popular>> {
        return movieApi.getPopularMovie()
            .map { it.results.mapToPopular() }
    }

    private fun List<PopularResponse>.mapToPopular(): List<Popular> {
        return map { response ->
            with(response) {
                Popular(
                    id,
                    title,
                    overview,
                    posterPath.orEmpty(),
                    backdropPath.orEmpty(),
                    releaseDate,
                    originalLanguage,
                    voteAverage,
                    voteCount,
                    genreIds
                )
            }
        }
    }

}