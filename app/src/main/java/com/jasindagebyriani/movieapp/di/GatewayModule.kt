package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.gateway.GenreGateway
import com.jasindagebyriani.movieapp.domain.gateway.GenreGatewayImpl
import com.jasindagebyriani.movieapp.domain.gateway.PopularGateway
import com.jasindagebyriani.movieapp.domain.gateway.PopularGatewayImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class GatewayModule {
    @Binds
    abstract fun bindPopularGateway(popularGateway: PopularGatewayImpl): PopularGateway

    @Binds
    abstract fun bindGenreGateway(genreGateway: GenreGatewayImpl): GenreGateway
}