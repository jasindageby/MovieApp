package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface FavoriteGateway {
    fun getAllFavorites(): Observable<List<MovieDatabaseEntity>>
}

class FavoriteGatewayImpl @Inject constructor(
    private val movieDao: MovieDao
) : FavoriteGateway {
    override fun getAllFavorites(): Observable<List<MovieDatabaseEntity>> {
        return movieDao.getAll()
    }
}