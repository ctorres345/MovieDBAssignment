package com.backbase.assignment.model

data class PopularMovieUIModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val duration: String,
    val posterImage: String,
    val roundedPopularity: Int,
    val progressPopularity: Float
)