package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.util.mapToMovieDatabaseEntity
import com.jasindagebyriani.movieapp.util.mapToMoviesViewObject
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class TopRatedPresenter @Inject constructor(
    private val topRatedUseCase: TopRatedUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : TopRatedContract.Presenter {
    private lateinit var view: TopRatedContract.View

    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: TopRatedContract.View) {
        this.view = view
    }

    override fun loadData() {
        topRatedUseCase.getTopRatedMovies()
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
}