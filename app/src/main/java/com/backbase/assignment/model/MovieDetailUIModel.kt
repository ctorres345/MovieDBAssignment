package com.backbase.assignment.model

data class MovieDetailUIModel(
    val id: Int,
    val adult: Boolean,
    val genres: List<MovieGenreUIModel>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int?,
    val title: String
)