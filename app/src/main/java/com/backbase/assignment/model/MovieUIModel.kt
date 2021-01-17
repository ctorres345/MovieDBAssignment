package com.backbase.assignment.model

data class MovieUIModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val duration: String,
    val adult: Boolean
)