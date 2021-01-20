package com.backbase.domain.model.movie

import java.math.BigDecimal

data class Movie(
    val id: Int,
    var backdropPath: String?,
    var genreIds: List<Int>,
    val overview: String,
    var posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: BigDecimal
)