package com.jasindagebyriani.movieapp.domain.usecase

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.gateway.FavoriteGateway
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface FavoriteUseCase {
    fun addFavorite(movieEntity: MovieDatabaseEntity): Completable
    fun getAllFavorites(): Observable<List<MovieDatabaseEntity>>
    fun removeFavorite(movieEntity: MovieDatabaseEntity): Completable
}

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteGateway: FavoriteGateway
) : FavoriteUseCase {
    override fun getAllFavorites(): Observable<List<MovieDatabaseEntity>> {
        return favoriteGateway.getAllFavorites()
    }

    override fun addFavorite(movieEntity: MovieDatabaseEntity): Completable {
        return favoriteGateway.addFavorite(movieEntity)
    }

    override fun removeFavorite(movieEntity: MovieDatabaseEntity): Completable {
        return favoriteGateway.removeFavorite(movieEntity)
    }
}