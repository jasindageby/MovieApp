package com.jasindagebyriani.movieapp.util

import androidx.room.TypeConverter

@TypeConverter
fun String.mapToList(): List<String> {
    return split(",").map { it }
}

@TypeConverter
fun List<String>.mapToString(): String {
    return joinToString(separator = ",")
}