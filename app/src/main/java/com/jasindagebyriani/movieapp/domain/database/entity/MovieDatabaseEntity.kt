package com.jasindagebyriani.movieapp.domain.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDatabaseEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Long,
    @ColumnInfo(name = "genre") val genre: String
)
