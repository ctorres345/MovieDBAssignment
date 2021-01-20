package com.backbase.domain.repository.moviedetail

import com.backbase.domain.model.moviedetail.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun clearMovieDetailData()
}