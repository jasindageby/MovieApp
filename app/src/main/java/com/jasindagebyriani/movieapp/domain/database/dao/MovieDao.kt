package com.jasindagebyriani.movieapp.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jasindagebyriani.movieapp.domain.database.entity.MovieDatabaseEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface MovieDao {
    @Query("select * from movie")
    fun getAll(): Observable<List<MovieDatabaseEntity>>

    @Insert
    fun insert(movie: MovieDatabaseEntity): Completable

    @Delete
    fun delete(movie: MovieDatabaseEntity): Completable
}