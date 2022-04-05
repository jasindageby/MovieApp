package com.jasindagebyriani.movieapp.util

import java.text.SimpleDateFormat
import java.util.*

fun String.convertReleaseDate(): String {
    val formatOutput = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val formatInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val input = formatInput.parse(this)
    return formatOutput.format(input)
}