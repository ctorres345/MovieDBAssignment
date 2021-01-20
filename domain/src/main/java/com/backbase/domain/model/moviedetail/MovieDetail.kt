package com.backbase.domain.model.moviedetail

import com.backbase.domain.model.genre.Genre
import java.math.BigDecimal

data class MovieDetail(
    val id: Int,
    val adult: Boolean,
    var backdropPath: String?,
    val genres: List<Genre>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: BigDecimal?,
    var posterPath: String?,
    val releaseDate: String,
    val status: String,
    val title: String,
    val video: Boolean,
    val runtime: Int?,
    val voteAverage: BigDecimal,
    val voteCount: Int
)