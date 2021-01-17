package com.example.domain.model

import java.math.BigDecimal

data class MovieDetail(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val collection: MovieCollection?,
    val budget: Int,
    val genres: List<MovieGenre>,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: BigDecimal?,
    val posterPath: String?,
    val productionCompanies: List<MovieCompany>,
    val productionCountries: List<MovieCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<MovieLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: BigDecimal,
    val voteCount: Int
)