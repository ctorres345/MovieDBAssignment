package com.example.domain.repository.movie

import com.example.domain.model.movie.MoviePage
import com.example.domain.model.MovieSection

interface MovieRepository {
    suspend fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage
    suspend fun clearMoviePagesForSection(section: MovieSection)
}