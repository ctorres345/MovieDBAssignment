package com.example.data.mapper

import com.example.data.model.MovieDTO
import com.example.domain.model.Movie
import java.math.BigDecimal

fun MovieDTO.toDomainModel() : Movie {
    return Movie(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}