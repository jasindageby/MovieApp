package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.util.convertReleaseDate
import com.jasindagebyriani.movieapp.util.mapToString
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PopularPresenter @Inject constructor(
    private val popularUseCase: PopularUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : PopularContract.Presenter {
    private lateinit var view: PopularContract.View

    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: PopularContract.View) {
        this.view = view
    }

    override fun loadData() {
        popularUseCase.getPopularMovies()
            .flatMap {
                favoriteUseCase.getAllFavorites()
                    .map { entities -> it to entities.map { it.id } }
            }
            .map { (movies, favoriteIds) ->
                mapToMoviesViewObject(movies, favoriteIds)
            }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                view.showList(it)
            }, {
                view.showError()
            })
            .let(compositeDisposable::add)
    }

    override fun clickFavorite(movieViewObject: MovieViewObject) {
        if (movieViewObject.isFavorite) {
            addToFavorite(movieViewObject)
        } else {
            removeFromFavorite(movieViewObject)
        }
    }

    override fun detachView() {
        compositeDisposable.clear()
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

    private fun mapToMoviesViewObject(
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
}