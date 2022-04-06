package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class PopularPresenterTest {
    private lateinit var popularUseCase: PopularUseCase
    private lateinit var favoriteUseCase: FavoriteUseCase
    private lateinit var presenter: PopularContract.Presenter
    private lateinit var view: PopularContract.View

    @Before
    fun setup() {
        popularUseCase = mock()
        favoriteUseCase = mock()
        view = mock()

        presenter = PopularPresenter(
            popularUseCase,
            favoriteUseCase,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `given data return success when loadData should return list of movie object`() {
        val movies = listOf(
            Movie(
                123L,
                "title",
                "overview",
                "/aiueo.jpg",
                "/qwerty.jpg",
                "2021-12-15",
                "en",
                8.8,
                321L,
                listOf("action", "comedy")
            )
        )

        whenever(popularUseCase.getPopularMovies()).thenReturn(Observable.just(movies))
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(Observable.just(emptyList()))

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
                false
            )
        )
        verify(view).showList(expectedMoviesViewObject)
    }

    @Test
    fun `given data popular return error when loadData should showError`() {
        whenever(popularUseCase.getPopularMovies()).thenReturn(Observable.error(Exception()))
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(Observable.just(emptyList()))

        presenter.attachView(view)
        presenter.loadData()

        verify(view).showError()
    }

    @Test
    fun `given data  favorite return error when loadData should showError`() {
        whenever(popularUseCase.getPopularMovies()).thenReturn(Observable.just(emptyList()))
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(Observable.error(Exception()))

        presenter.attachView(view)
        presenter.loadData()

        verify(view).showError()
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

        presenter.attachView(view)
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

        presenter.attachView(view)
        presenter.clickFavorite(viewObject)

        verify(favoriteUseCase).removeFavorite(entity)
    }
}