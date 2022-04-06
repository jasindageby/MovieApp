package com.jasindagebyriani.movieapp.util

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

fun MovieViewObject.mapToMovieDatabaseEntity(): MovieDatabaseEntity {
    return MovieDatabaseEntity(
        id,
        title,
        overview,
        posterPath,
        backdropPath,
        releaseDate,
        originalLanguage,
        voteAverage,
        voteCount,
        genre.mapToString()
    )
}

fun mapToMoviesViewObject(
    movies: List<Movie>,
    favoriteIds: List<Long>
): List<MovieViewObject> {
    return movies.map { movie ->
        val isFavorite = favoriteIds.contains(movie.id)
        with(movie) {
            MovieViewObject(
                id,
                title,
                overview,
                getPosterPath(),
                getBackdropPath(),
                releaseDate.convertReleaseDate(),
                originalLanguage,
                voteAverage,
                voteCount,
                genre,
                isFavorite
            )
        }
    }
}