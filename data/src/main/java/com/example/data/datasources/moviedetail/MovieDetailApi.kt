package com.example.data.datasources.moviedetail

import com.example.domain.model.moviedetail.MovieDetail

interface MovieDetailApi {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}