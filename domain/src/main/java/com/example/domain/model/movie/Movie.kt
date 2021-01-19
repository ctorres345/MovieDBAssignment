package com.example.domain.model.movie

import java.math.BigDecimal

data class Movie(
    val id: Int,
    val adult: Boolean,
    var backdropPath: String?,
    var genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: BigDecimal,
    var posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: BigDecimal,
    val voteCount: Int
)