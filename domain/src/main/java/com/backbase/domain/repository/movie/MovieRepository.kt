package com.backbase.domain.repository.movie

import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.movie.MoviePage

interface MovieRepository {
    suspend fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage
    suspend fun clearMoviePagesForSection(section: MovieSection)
}