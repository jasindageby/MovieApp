package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.MovieApi
import com.jasindagebyriani.movieapp.domain.api.response.TopRatedResponse
import com.jasindagebyriani.movieapp.domain.entity.TopRated
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface TopRatedGateway {
    fun getTopRatedMovies(): Observable<List<TopRated>>
}

class TopRatedGatewayImpl @Inject constructor(
    private val movieApi: MovieApi
) : TopRatedGateway {
    override fun getTopRatedMovies(): Observable<List<TopRated>> {
        return movieApi.getTopRatedMovie()
            .map { it.results.mapToRated()}
    }

    private fun List<TopRatedResponse>.mapToRated(): List<TopRated> {
        return map { response ->
            with(response) {
                TopRated(
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