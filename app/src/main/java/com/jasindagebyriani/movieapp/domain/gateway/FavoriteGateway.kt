package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface FavoriteGateway {
    fun addFavorite(movieEntity: MovieDatabaseEntity): Completable
    fun getAllFavorites(): Observable<List<MovieDatabaseEntity>>
    fun removeFavorite(movieEntity: MovieDatabaseEntity): Completable
}

class FavoriteGatewayImpl @Inject constructor(
    private val movieDao: MovieDao
) : FavoriteGateway {
    override fun getAllFavorites(): Observable<List<MovieDatabaseEntity>> {
        return movieDao.getAll()
    }

    override fun addFavorite(movieEntity: MovieDatabaseEntity): Completable {
        return movieDao.insert(movieEntity)
    }

    override fun removeFavorite(movieEntity: MovieDatabaseEntity): Completable {
        return movieDao.delete(movieEntity)
    }
}