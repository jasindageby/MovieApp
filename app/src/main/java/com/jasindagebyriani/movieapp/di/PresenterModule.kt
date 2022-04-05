package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.presenter.PopularContract
import com.jasindagebyriani.movieapp.presenter.PopularPresenter
import com.jasindagebyriani.movieapp.presenter.TopRatedContract
import com.jasindagebyriani.movieapp.presenter.TopRatedPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(ActivityComponent::class)
class PresenterModule {

    @Provides
    fun providePopularPresenter(
        popularUseCase: PopularUseCase
    ): PopularContract.Presenter {
        return PopularPresenter(
            popularUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideTopRatedPresenter(
        topRatedUseCase: TopRatedUseCase
    ): TopRatedContract.Presenter {
        return TopRatedPresenter(
            topRatedUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }
}