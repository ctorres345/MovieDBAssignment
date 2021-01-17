package com.example.data.mapper

import com.example.data.model.MovieDetailDTO
import com.example.domain.model.MovieDetail
import java.math.BigDecimal

fun MovieDetailDTO.toDomainModel() : MovieDetail {
    return MovieDetail(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        collection = collection?.toDomainModel(),
        budget = budget,
        genres = genres.map { it.toDomainModel() },
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies.map { it.toDomainModel() },
        productionCountries = productionCountries.map { it.toDomainModel() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toDomainModel() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
