package com.backbase.data.datasources.moviepage

import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.model.MovieSection

interface MoviePageDB {
    fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage?
    fun saveMoviePageForSection(moviePage: MoviePage, section: MovieSection)
    fun clearAllPagesInSection(section: MovieSection)
}