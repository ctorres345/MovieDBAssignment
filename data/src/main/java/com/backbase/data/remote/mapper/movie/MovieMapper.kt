package com.backbase.data.remote.mapper.movie

import com.backbase.data.remote.model.MovieDTO
import com.backbase.data.remote.model.MoviePageDTO
import com.backbase.domain.model.movie.Movie
import com.backbase.domain.model.movie.MoviePage

fun MoviePageDTO.toDomainModel() : MoviePage {
    return MoviePage(
        page = page,
        results = results.map { it.toDomainModel() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

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