package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCaseImpl
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindPopularUseCase(popularUseCase: PopularUseCaseImpl): PopularUseCase

    @Binds
    abstract fun bindTopRatedUseCase(topRatedUseCase: TopRatedUseCaseImpl): TopRatedUseCase

}