package com.backbase.data.datasources.moviedetail

import com.backbase.domain.model.moviedetail.MovieDetail

interface MovieDetailApi {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}