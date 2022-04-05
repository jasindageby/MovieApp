package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class FavoritePresenter @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : FavoriteContract.Presenter {

    private lateinit var view: FavoriteContract.View

    private val disposable = CompositeDisposable()

    override fun attachView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun loadData() {
        favoriteUseCase.getAllFavorites()
            .map { it.mapToViewObject() }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                if (it.isEmpty()) {
                    view.showEmptyList()
                } else {
                    view.showList(it)
                }
            }, {
                view.showError()
            })
    }

    private fun List<MovieDatabaseEntity>.mapToViewObject(): List<MovieViewObject> {
        return map { entity ->
            with(entity) {
                MovieViewObject(
                    id,
                    title,
                    overview,
                    posterPath,
                    backdropPath,
                    releaseDate,
                    originalLanguage,
                    voteAverage,
                    voteCount,
                    genre
                )
            }
        }
    }

    override fun detachView() {
        disposable.clear()
    }
}