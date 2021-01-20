package com.backbase.domain.model.moviedetail

import com.backbase.domain.model.genre.Genre
import java.math.BigDecimal

data class MovieDetail(
    val id: Int,
    var backdropPath: String?,
    val genres: List<Genre>,
    val overview: String?,
    var posterPath: String?,
    val releaseDate: String,
    val title: String,
    val runtime: Int?,
    val voteAverage: BigDecimal
)