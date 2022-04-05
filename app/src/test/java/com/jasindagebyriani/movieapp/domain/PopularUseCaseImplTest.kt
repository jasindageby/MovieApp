package com.jasindagebyriani.movieapp.domain

import com.jasindagebyriani.movieapp.domain.entity.Genre
import com.jasindagebyriani.movieapp.domain.entity.Movie
import com.jasindagebyriani.movieapp.domain.entity.Popular
import com.jasindagebyriani.movieapp.domain.gateway.GenreGateway
import com.jasindagebyriani.movieapp.domain.gateway.PopularGateway
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCaseImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class PopularUseCaseImplTest {
    private lateinit var genreGateway: GenreGateway
    private lateinit var popularGateway: PopularGateway
    private lateinit var popularUseCase: PopularUseCase

    @Before
    fun setup() {
        genreGateway = mock()
        popularGateway = mock()
        popularUseCase = PopularUseCaseImpl(popularGateway, genreGateway)
    }

    @Test
    fun `when getPopulars should return list of movie`() {
        val populars = listOf(
            Popular(
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

        whenever(popularGateway.getPopularMovies()).thenReturn(Observable.just(populars))
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

        popularUseCase.getPopularMovies().test()
            .assertValue(expectedMovie)
            .assertNoErrors()
    }
}