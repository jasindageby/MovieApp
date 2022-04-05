package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.util.convertReleaseDate
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PopularPresenter @Inject constructor(
    private val popularUseCase: PopularUseCase,
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
            .map { it.mapToMoviesViewObject() }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                view.showList(it)
            }, {
                view.showError()
            })
            .let(compositeDisposable::add)
    }

    private fun List<Movie>.mapToMoviesViewObject(): List<MovieViewObject> {
        return map { movie ->
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
                    genre
                )
            }
        }
    }

    override fun detachView() {
        compositeDisposable.clear()
    }
}