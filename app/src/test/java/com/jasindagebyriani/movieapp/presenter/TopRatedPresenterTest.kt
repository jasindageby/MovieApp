package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class TopRatedPresenterTest {
    private lateinit var topRatedUseCase: TopRatedUseCase
    private lateinit var presenter: TopRatedContract.Presenter
    private lateinit var view: TopRatedContract.View

    @Before
    fun setup() {
        topRatedUseCase = mock()
        view = mock()

        presenter = TopRatedPresenter(
            topRatedUseCase,
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

        whenever(topRatedUseCase.getTopRatedMovies()).thenReturn(Observable.just(movies))

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
                listOf("action", "comedy")
            )
        )
        verify(view).showList(expectedMoviesViewObject)
    }

    @Test
    fun `given data return error when loadData should showError`() {
        whenever(topRatedUseCase.getTopRatedMovies()).thenReturn(Observable.error(Exception()))

        presenter.attachView(view)
        presenter.loadData()

        verify(view).showError()
    }
}