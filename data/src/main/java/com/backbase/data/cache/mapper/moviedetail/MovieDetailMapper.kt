package com.backbase.data.cache.mapper.moviedetail

import com.backbase.data.cache.room.tables.MovieDetailEntity
import com.backbase.data.cache.room.tables.MovieGenreEntity
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.model.moviedetail.MovieDetail

fun MovieDetailEntity.toDomainModel(genres: List<MovieGenreEntity>) : MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        genres = genres.map { it.toDomainModel() },
        voteAverage = voteAverage.toBigDecimal(),
        backdropPath = backdropPath,
        runtime = runtime
    )
}

fun MovieGenreEntity.toDomainModel() : Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieDetail.toRoomModel(dueDate: Long) : MovieDetailEntity {
    return MovieDetailEntity(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        voteAverage = voteAverage.toDouble(),
        backdropPath = backdropPath,
        runtime = runtime,
        dueDate = dueDate
    )
}

fun Genre.toMovieGenreRoomModel(movieDetailId: Int) : MovieGenreEntity {
    return MovieGenreEntity(
        id = id,
        name = name,
        movieDetailId = movieDetailId
    )
}