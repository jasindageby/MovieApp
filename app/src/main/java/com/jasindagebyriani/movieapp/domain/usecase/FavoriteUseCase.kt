package com.jasindagebyriani.movieapp.domain.usecase

import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import com.jasindagebyriani.movieapp.domain.gateway.FavoriteGateway
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface FavoriteUseCase {
    fun getAllFavorites(): Observable<List<MovieDatabaseEntity>>
}

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteGateway: FavoriteGateway
) : FavoriteUseCase {
    override fun getAllFavorites(): Observable<List<MovieDatabaseEntity>> {
        return favoriteGateway.getAllFavorites()
    }
}