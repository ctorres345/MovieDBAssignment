package com.example.domain.repository.moviedetail

import com.example.domain.model.moviedetail.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun clearMovieDetailData()
}