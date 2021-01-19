package com.example.data.datasources.moviepage

import com.example.domain.model.movie.MoviePage

interface MoviePageApi {
    suspend fun getBillboardMoviePage(page: Int) : MoviePage
    suspend fun getPopularMoviePage(page: Int) : MoviePage
}