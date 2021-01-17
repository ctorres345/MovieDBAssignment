package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieDetailUIModel
import com.example.domain.model.MovieDetail

fun MovieDetail.toUIModel() : MovieDetailUIModel {
    return MovieDetailUIModel(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        collection = collection?.toUIModel(),
        budget = budget,
        genres = genres.map { it.toUIModel() },
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies.map { it.toUIModel() },
        productionCountries = productionCountries.map { it.toUIModel() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toUIModel() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
