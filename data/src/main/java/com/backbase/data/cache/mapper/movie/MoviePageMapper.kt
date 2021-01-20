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
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage.toBigDecimal(),
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
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage.toDouble(),
        pageId = pageId
    )
}