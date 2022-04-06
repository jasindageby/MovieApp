package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.util.mapToString
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : MovieDetailContract.Presenter {

    override fun clickFavorite(movieViewObject: MovieViewObject) {
        if (movieViewObject.isFavorite) {
            addToFavorite(movieViewObject)
        } else {
            removeFromFavorite(movieViewObject)
        }
    }

    private fun addToFavorite(movieViewObject: MovieViewObject) {
        favoriteUseCase.addFavorite(movieViewObject.mapToMovieDatabaseEntity())
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({}, {})
    }

    private fun removeFromFavorite(movieViewObject: MovieViewObject) {
        favoriteUseCase.removeFavorite(movieViewObject.mapToMovieDatabaseEntity())
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({}, {})
    }

    private fun MovieViewObject.mapToMovieDatabaseEntity(): MovieDatabaseEntity {
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

}