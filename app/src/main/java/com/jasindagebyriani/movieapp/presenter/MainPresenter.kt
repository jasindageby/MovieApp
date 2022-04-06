package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    private val disposable = CompositeDisposable()

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun loadTotalFavorite() {
        favoriteUseCase.getAllFavorites()
            .map { it.size }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                view.showTotalFavorite(it)
            }, {

            })
    }

    override fun detachView() {
        disposable.clear()
    }
}