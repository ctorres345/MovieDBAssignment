package com.backbase.data.cache.mapper.movie

import com.backbase.data.cache.room.tables.MovieEntity
import com.backbase.data.cache.room.tables.MoviePageEntity
import com.backbase.domain.model.movie.Movie
import com.backbase.domain.model.movie.MoviePage

fun MoviePageEntity.toDomainModel(movies: List<MovieEntity>) : MoviePage {
    return MoviePage(
        page = page,
        results = movies.map { it.toDomainModel() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun MovieEntity.toDomainModel() : Movie {
    return Movie(
        id = movieId,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteCount = voteCount,
        voteAverage = voteAverage.toBigDecimal(),
        popularity = popularity.toBigDecimal(),
        adult = adult,
        video = video,
        genreIds = emptyList()
    )
}

fun MoviePage.toRoomModel(sectionName: String, dueDate: Long) : MoviePageEntity {
    return MoviePageEntity(
        page = page,
        totalPages = totalPages,
        totalResults = totalResults,
        section = sectionName,
        dueDate = dueDate
    )
}

fun Movie.toRoomModel(pageId: Int) : MovieEntity {
    return MovieEntity(
        movieId = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteCount = voteCount,
        voteAverage = voteAverage.toDouble(),
        popularity = popularity.toDouble(),
        adult = adult,
        video = video,
        pageId = pageId
    )
}