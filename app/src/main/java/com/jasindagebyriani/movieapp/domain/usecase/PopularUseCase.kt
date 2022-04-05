package com.jasindagebyriani.movieapp.domain.usecase

import com.jasindagebyriani.movieapp.domain.entity.Genre
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.entity.Popular
import com.jasindagebyriani.movieapp.domain.gateway.GenreGateway
import com.jasindagebyriani.movieapp.domain.gateway.PopularGateway
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface PopularUseCase {
    fun getPopularMovies(): Observable<List<Movie>>
}

class PopularUseCaseImpl @Inject constructor(
    private val popularGateway: PopularGateway,
    private val genreGateway: GenreGateway
) : PopularUseCase {

    override fun getPopularMovies(): Observable<List<Movie>> {
        return Observable.combineLatest(
            popularGateway.getPopularMovies(),
            genreGateway.getGenre()
        ) { populars, genres -> mapToMovie(populars, genres) }
    }

    private fun mapToMovie(populars: List<Popular>, genres: List<Genre>): List<Movie> {
        return populars.map { popular ->
            with(popular) {
                Movie(
                    id,
                    title,
                    overview,
                    posterPath,
                    backdropPath,
                    releaseDate,
                    originalLanguage,
                    voteAverage,
                    voteCount,
                    getNameOfGenre(genreIds, genres)
                )
            }
        }
    }

    private fun getNameOfGenre(genreIds: List<Long>, genres: List<Genre>): List<String> {
        return genreIds.map { genreId ->
            genres.first { it.id == genreId }.name
        }
    }
}