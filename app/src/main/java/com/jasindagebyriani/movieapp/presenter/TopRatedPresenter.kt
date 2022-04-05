package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TopRatedPresenter @Inject constructor(
    private val topRatedUseCase: TopRatedUseCase,
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

    private fun String.convertReleaseDate(): String {
        val formatOutput = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formatInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val input = formatInput.parse(this)
        return formatOutput.format(input)
    }

    override fun detachView() {
        compositeDisposable.clear()
    }
}