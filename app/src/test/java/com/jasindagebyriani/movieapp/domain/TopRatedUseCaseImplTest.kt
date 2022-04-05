package com.jasindagebyriani.movieapp.domain

import com.jasindagebyriani.movieapp.domain.entity.Genre
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.entity.TopRated
import com.jasindagebyriani.movieapp.domain.gateway.GenreGateway
import com.jasindagebyriani.movieapp.domain.gateway.TopRatedGateway
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCaseImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class TopRatedUseCaseImplTest {
    private lateinit var genreGateway: GenreGateway
    private lateinit var topRatedGateway: TopRatedGateway
    private lateinit var topRatedUseCase: TopRatedUseCase

    @Before
    fun setup() {
        genreGateway = mock()
        topRatedGateway = mock()
        topRatedUseCase = TopRatedUseCaseImpl(topRatedGateway, genreGateway)
    }

    @Test
    fun `when getTopRatedMovies should return list of movie`() {
        val topRatedList = listOf(
            TopRated(
                123L,
                "title",
                "overview",
                "aiueo.jpg",
                "qwerty.jpg",
                "12-12-2000",
                "en",
                8.8,
                321L,
                listOf(1, 2)
            )
        )
        val genres = listOf(
            Genre(1, "action"),
            Genre(2, "comedy")
        )

        whenever(topRatedGateway.getTopRatedMovies()).thenReturn(Observable.just(topRatedList))
        whenever(genreGateway.getGenre()).thenReturn(Observable.just(genres))

        val expectedMovie = listOf(
            Movie(
                123L,
                "title",
                "overview",
                "aiueo.jpg",
                "qwerty.jpg",
                "12-12-2000",
                "en",
                8.8,
                321L,
                listOf("action", "comedy")
            )
        )

        topRatedUseCase.getTopRatedMovies().test()
            .assertValue(expectedMovie)
            .assertNoErrors()
    }
}