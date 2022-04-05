package com.jasindagebyriani.movieapp.di

import android.content.Context
import com.jasindagebyriani.movieapp.domain.database.AppDatabase
import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.create(context).build()
    }

    @Provides
    fun provideMovieDao(
        appDatabase: AppDatabase
    ): MovieDao {
        return appDatabase.movieDao()
    }
}