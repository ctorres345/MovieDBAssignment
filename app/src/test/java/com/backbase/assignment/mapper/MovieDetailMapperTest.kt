package com.backbase.assignment.mapper

import com.backbase.assignment.presentation.ext.toDate
import com.backbase.assignment.presentation.ext.toReleaseDate
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.model.moviedetail.MovieDetail
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MovieDetailMapperTest {
    private val movieDetail = MovieDetail(
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

    @Test
    fun `Given a MovieDetail object, then the mapper function transform it into a valid MovieDetailUIModel object`() {
        val result = movieDetail.toUIModel()
        assertEquals(movieDetail.id, result.id)
        assertEquals(movieDetail.overview, result.overview)
        assertEquals(movieDetail.posterPath, result.posterPath)
        assertEquals(movieDetail.releaseDate.toDate()!!.toReleaseDate(), result.releaseDate)
        assertEquals(movieDetail.title, result.title)
        assertEquals(movieDetail.runtime, result.runtime)
        assertTrue(result.genres.isNotEmpty())
    }

    @Test
    fun `Given a MovieDetailUIModel with a null runtime, then the mapper return null when asking for hours and minute`() {
        val movieDetailUIModel = movieDetail.toUIModel().apply { runtime = null }
        val pair = movieDetailUIModel.getHourAndMinutesRuntime()
        assertNull(pair)
    }
}