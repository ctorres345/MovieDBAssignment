package com.example.data.datasources.moviepage

import com.example.domain.model.movie.MoviePage
import com.example.domain.model.MovieSection

interface MoviePageDB {
    fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage?
    fun saveMoviePageForSection(moviePage: MoviePage, section: MovieSection)
    fun clearAllPagesInSection(section: MovieSection)
}