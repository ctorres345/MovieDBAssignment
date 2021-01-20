package com.backbase.domain.util

import com.backbase.testutils.TestStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class ModelExtTest {

    @Test
    fun `Given a valid Movie object and ImageConfiguration, then the extensions apply the correct format to the image path`() {
        val expectedPosterPath = "https://image.tmdb.org/t/p/original${TestStubs.movie.posterPath}"
        val expectedBackdropPath = "https://image.tmdb.org/t/p/original${TestStubs.movie.backdropPath}"
        val formatedMovie = TestStubs.movie.configurePaths(TestStubs.imageConfig)
        assertEquals(expectedPosterPath, formatedMovie.posterPath)
        assertEquals(expectedBackdropPath, formatedMovie.backdropPath)
    }

    @Test
    fun `Given a valid MovieDetail object and ImageConfiguration, then the extensions apply the correct format to the image path`() {
        val expectedPosterPath = "https://image.tmdb.org/t/p/original${TestStubs.movieDetail.posterPath}"
        val expectedBackdropPath = "https://image.tmdb.org/t/p/original${TestStubs.movieDetail.backdropPath}"
        val formatedMovieDetail = TestStubs.movieDetail.configurePaths(TestStubs.imageConfig)
        assertEquals(expectedPosterPath, formatedMovieDetail.posterPath)
        assertEquals(expectedBackdropPath, formatedMovieDetail.backdropPath)
    }
}