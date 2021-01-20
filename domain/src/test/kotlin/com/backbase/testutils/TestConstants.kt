package com.backbase.testutils

import com.backbase.domain.model.configuration.ImageConfiguration
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.model.movie.Movie
import com.backbase.domain.model.moviedetail.MovieDetail
import java.math.BigDecimal

object TestStubs {
    val imageConfig = ImageConfiguration(
        baseUrl = "http://image.tmdb.org/t/p/",
        secureUrl = "https://image.tmdb.org/t/p/",
        backdropSizes = listOf("w300","w780","w1280","original"),
        posterSizes = listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")
    )

    val movieDetail = MovieDetail(
        id = 464052,
        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
        genres = listOf(
            Genre(14, "Fantasy"),
            Genre(28, "Action"),
            Genre(12, "Adventure")
        ),
        overview = "test",
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        releaseDate = "2020-12-16",
        title =  "Wonder Woman 1984",
        runtime = 151,
        voteAverage = BigDecimal(7.1),
    )

    val movie = Movie(
        id = 464052,
        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
        genreIds = listOf(14, 28, 12),
        overview = "test",
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        releaseDate = "2020-12-16",
        title =  "Wonder Woman 1984",
        voteAverage = BigDecimal(7.1),
    )
}