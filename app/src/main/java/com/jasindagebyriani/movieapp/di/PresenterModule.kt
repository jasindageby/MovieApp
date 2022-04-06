package com.jasindagebyriani.movieapp.di

import com.jasindagebyriani.movieapp.domain.usecase.FavoriteUseCase
import com.jasindagebyriani.movieapp.domain.usecase.PopularUseCase
import com.jasindagebyriani.movieapp.domain.usecase.TopRatedUseCase
import com.jasindagebyriani.movieapp.presenter.*
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
        popularUseCase: PopularUseCase,
        favoriteUseCase: FavoriteUseCase
    ): PopularContract.Presenter {
        return PopularPresenter(
            popularUseCase,
            favoriteUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideTopRatedPresenter(
        topRatedUseCase: TopRatedUseCase,
        favoriteUseCase: FavoriteUseCase
    ): TopRatedContract.Presenter {
        return TopRatedPresenter(
            topRatedUseCase,
            favoriteUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideFavoritePresenter(
        favoriteUseCase: FavoriteUseCase
    ): FavoriteContract.Presenter {
        return FavoritePresenter(
            favoriteUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideMovieDetailPresenter(
        favoriteUseCase: FavoriteUseCase
    ): MovieDetailContract.Presenter {
        return MovieDetailPresenter(
            favoriteUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideMainPresenter(
        favoriteUseCase: FavoriteUseCase
    ): MainContract.Presenter {
        return MainPresenter(
            favoriteUseCase,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }
}