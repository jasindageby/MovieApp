package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var favoriteUseCase: FavoriteUseCase

    @Before
    fun setup() {
        favoriteUseCase = mock()
        view = mock()

        presenter = MainPresenter(
            favoriteUseCase,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `given list not empty when load total should trigger update layout`() {
        val list = listOf(
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
                "action,comedy"
            )
        )
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(Observable.just(list))

        presenter.attachView(view)
        presenter.loadTotalFavorite()

        verify(view).showTotalFavorite(list.size)
    }
}