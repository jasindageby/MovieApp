package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.presenter.PopularContract
import com.jasindagebyriani.movieapp.view.PopularFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModule {
    @Binds
    abstract fun bindView(fragment: PopularFragment): PopularContract.View
}