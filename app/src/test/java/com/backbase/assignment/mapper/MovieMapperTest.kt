package com.backbase.assignment.mapper

import com.backbase.assignment.presentation.ext.toDate
import com.backbase.assignment.presentation.ext.toReleaseDate
import com.backbase.domain.model.movie.Movie
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class MovieMapperTest {
    private val movie = Movie(
        id = 464052,
        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
        genreIds = listOf(14, 28, 12),
        overview = "test",
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        releaseDate = "2020-12-16",
        title =  "Wonder Woman 1984",
        voteAverage = BigDecimal(7.1),
    )

    @Test
    fun `Given a Movie object, then the mapper function transform it into a valid PopularMovieUIModel object`() {
        val result = movie.toPopularMovie()
        assertEquals(movie.id, result.id)
        assertEquals(movie.releaseDate.toDate()!!.toReleaseDate(), result.releaseDate)
        assertEquals(movie.title, result.title)
        assertEquals(movie.posterPath, result.posterImage)
        assertEquals(movie.getRoundedRating(), result.roundedPopularity)
        assertEquals(movie.getProgressRating(), result.progressPopularity)
    }

    @Test
    fun `Given a Movie object, then the mapper function transform it into a valid BillboardMovieUIModel object`() {
        val result = movie.toBillboardMovie()
        assertEquals(movie.posterPath, result.posterImage)
    }

    @Test
    fun `Given a Movie object with valid movie rating, then the mapper return the rating in a valid format for the rating view`() {
        val expected = 0.7f
        val result = movie.getProgressRating()
        assertEquals(expected, result)
    }

    @Test
    fun `Given a Movie object with valid movie rating, then the mapper return the rating in a valid rounded format for the rating view`() {
        val expected = 71
        val result = movie.getRoundedRating()
        assertEquals(expected, result)
    }
}