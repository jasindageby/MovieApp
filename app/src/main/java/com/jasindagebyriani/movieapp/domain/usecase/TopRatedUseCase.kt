package com.jasindagebyriani.movieapp.domain.usecase

import com.jasindagebyriani.movieapp.domain.entity.Genre
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.entity.TopRated
import com.jasindagebyriani.movieapp.domain.gateway.GenreGateway
import com.jasindagebyriani.movieapp.domain.gateway.TopRatedGateway
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface TopRatedUseCase {
    fun getTopRatedMovies(): Observable<List<Movie>>
}

class TopRatedUseCaseImpl @Inject constructor(
    private val topRatedGateway: TopRatedGateway,
    private val genreGateway: GenreGateway
) : TopRatedUseCase {

    override fun getTopRatedMovies(): Observable<List<Movie>> {
        return Observable.combineLatest(
            topRatedGateway.getTopRatedMovies(),
            genreGateway.getGenre()
        ) { topRated, genres -> mapToMovie(topRated, genres) }
    }

    private fun mapToMovie(topRatedList: List<TopRated>, genres: List<Genre>): List<Movie> {
        return topRatedList.map { topRated ->
            with(topRated) {
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