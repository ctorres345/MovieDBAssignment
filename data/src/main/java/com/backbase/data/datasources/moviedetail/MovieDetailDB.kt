package com.backbase.data.datasources.moviedetail

import com.backbase.domain.model.moviedetail.MovieDetail

interface MovieDetailDB {
    fun getMovieDetail(movieId: Int): MovieDetail?
    fun saveMovieDetail(movieDetail: MovieDetail)
    fun clearData()
}