package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class MovieDetailPresenterTest {
    private lateinit var presenter: MovieDetailContract.Presenter
    private lateinit var favoriteUseCase: FavoriteUseCase

    @Before
    fun setup() {
        favoriteUseCase = mock()
        presenter = MovieDetailPresenter(
            favoriteUseCase,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `given data is favorite when clickFavorite should add data to favorite`() {
        val viewObject = MovieViewObject(
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

        val entity = MovieDatabaseEntity(
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

        whenever(favoriteUseCase.addFavorite(entity)).thenReturn(Completable.complete())

        presenter.clickFavorite(viewObject)

        verify(favoriteUseCase).addFavorite(entity)
    }

    @Test
    fun `given data is not favorite when clickFavorite should remove data from favorite`() {
        val viewObject = MovieViewObject(
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
            false
        )

        val entity = MovieDatabaseEntity(
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

        whenever(favoriteUseCase.removeFavorite(entity)).thenReturn(Completable.complete())

        presenter.clickFavorite(viewObject)

        verify(favoriteUseCase).removeFavorite(entity)
    }


}