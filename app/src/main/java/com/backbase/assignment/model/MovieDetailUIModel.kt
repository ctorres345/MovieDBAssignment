package com.backbase.assignment.model

import java.math.BigDecimal

data class MovieDetailUIModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val collection: MovieCollectionUIModel?,
    val budget: Int,
    val genres: List<MovieGenreUIModel>,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: BigDecimal?,
    val posterPath: String?,
    val productionCompanies: List<MovieCompanyUIModel>,
    val productionCountries: List<MovieCountryUIModel>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<MovieLanguageUIModel>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: BigDecimal,
    val voteCount: Int
)