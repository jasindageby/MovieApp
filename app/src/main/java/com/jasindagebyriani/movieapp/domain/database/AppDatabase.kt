package com.jasindagebyriani.movieapp.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasindagebyriani.movieapp.domain.database.dao.MovieDao
import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity

@Database(entities = [MovieDatabaseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context): Builder<AppDatabase> {
            return Room.databaseBuilder(context, AppDatabase::class.java, "AppRoom.db")
        }
    }
}