package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class FavoritePresenterTest {
    private lateinit var useCase: FavoriteUseCase
    private lateinit var presenter: FavoriteContract.Presenter
    private lateinit var view: FavoriteContract.View

    @Before
    fun setup() {
        useCase = mock()
        view = mock()
        presenter = FavoritePresenter(useCase, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `given data return success with empty list when loadData should show empty list`() {
        whenever(useCase.getAllFavorites()).thenReturn(Observable.just(emptyList()))

        presenter.attachView(view)
        presenter.loadData()

        verify(view).showEmptyList()
    }

    @Test
    fun `given data return success when loadData should return show of movie object`() {
        val movies = listOf(
            MovieDatabaseEntity(
                123L,
                "title",
                "overview",
                "https://image.tmdb.org/t/p/w500/aiueo.jpg",
                "https://image.tmdb.org/t/p/w500/qwerty.jpg",
                "15 December 2021",
                "en",
                8.8,
                321L,
                "action, comedy"
            )
        )

        whenever(useCase.getAllFavorites()).thenReturn(Observable.just(movies))

        presenter.attachView(view)
        presenter.loadData()

        val expectedMoviesViewObject = listOf(
            MovieViewObject(
                123L,
                "title",
                "overview",
                "https://image.tmdb.org/t/p/w500/aiueo.jpg",
                "https://image.tmdb.org/t/p/w500/qwerty.jpg",
                "15 December 2021",
                "en",
                8.8,
                321L,
                listOf("action", "comedy"),
                true
            )
        )
        verify(view).showList(expectedMoviesViewObject)
    }

    @Test
    fun `given data return error when loadData should showError`() {
        whenever(useCase.getAllFavorites()).thenReturn(Observable.error(Exception()))

        presenter.attachView(view)
        presenter.loadData()

        verify(view).showError()
    }
}