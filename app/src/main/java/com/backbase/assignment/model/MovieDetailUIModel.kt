package com.backbase.assignment.model

data class MovieDetailUIModel(
    val id: Int,
    val genres: List<MovieGenreUIModel>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    var runtime: Int?,
    val title: String
)