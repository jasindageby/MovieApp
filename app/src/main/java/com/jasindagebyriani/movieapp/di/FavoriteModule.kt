package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import com.jasindagebyriani.movieapp.domain.gateway.FavoriteGateway
import com.jasindagebyriani.movieapp.domain.gateway.FavoriteGatewayImpl
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoriteModule {

    @Singleton
    @Provides
    fun bindFavoriteGateway(
        movieDao: MovieDao
    ): FavoriteGateway {
        return FavoriteGatewayImpl(movieDao)
    }

    @Singleton
    @Provides
    fun bindFavoriteUseCase(
        favoriteGateway: FavoriteGateway
    ): FavoriteUseCase {
        return FavoriteUseCaseImpl(favoriteGateway)
    }

}