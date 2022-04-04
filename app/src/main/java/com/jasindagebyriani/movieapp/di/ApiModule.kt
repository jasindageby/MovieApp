package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class ApiModule {
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}