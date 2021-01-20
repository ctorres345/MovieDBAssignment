package com.backbase.data.remote.mapper.moviedetail

import com.backbase.data.remote.mapper.genre.toDomainModel
import com.backbase.data.remote.model.MovieDetailDTO
import com.backbase.domain.model.moviedetail.MovieDetail

fun MovieDetailDTO.toDomainModel() : MovieDetail {
    return MovieDetail(
        id = id,
        backdropPath = backdropPath,
        genres = genres.map { it.toDomainModel() },
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        runtime = runtime
    )
}
