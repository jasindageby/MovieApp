package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class FavoriteGatewayImplTest {
    private lateinit var movieDao: MovieDao
    private lateinit var favoriteGateway: FavoriteGateway

    @Before
    fun setup() {
        movieDao = mock()
        favoriteGateway = FavoriteGatewayImpl(movieDao)
    }

    @Test
    fun `when getAllFavorites should get all data from database`() {
        val movieList = listOf(
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
                listOf("action", "comedy")
            )
        )

        whenever(movieDao.getAll()).thenReturn(Observable.just(movieList))

        favoriteGateway.getAllFavorites().test()
            .assertValue(movieList)
    }
}