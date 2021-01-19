package com.example.data.remote.mapper.moviedetail

import com.example.data.remote.mapper.genre.toDomainModel
import com.example.data.remote.model.MovieDetailDTO
import com.example.domain.model.moviedetail.MovieDetail

fun MovieDetailDTO.toDomainModel() : MovieDetail {
    return MovieDetail(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genres = genres.map { it.toDomainModel() },
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        status = status,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtime = runtime
    )
}
