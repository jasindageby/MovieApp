package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.presenter.FavoriteContract
import com.jasindagebyriani.movieapp.presenter.PopularContract
import com.jasindagebyriani.movieapp.presenter.TopRatedContract
import com.jasindagebyriani.movieapp.view.FavoriteFragment
import com.jasindagebyriani.movieapp.view.PopularFragment
import com.jasindagebyriani.movieapp.view.TopRatedFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModule {
    @Binds
    abstract fun bindPopularFragment(fragment: PopularFragment): PopularContract.View

    @Binds
    abstract fun bindTopRatedFragment(fragment: TopRatedFragment): TopRatedContract.View

    @Binds
    abstract fun bindFavoriteFragment(fragment: FavoriteFragment): FavoriteContract.View
}