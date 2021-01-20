package com.backbase.data.datasources.moviepage

import com.backbase.domain.model.movie.MoviePage

interface MoviePageApi {
    suspend fun getBillboardMoviePage(page: Int) : MoviePage
    suspend fun getPopularMoviePage(page: Int) : MoviePage
}