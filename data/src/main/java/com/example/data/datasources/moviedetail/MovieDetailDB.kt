package com.example.data.datasources.moviedetail

import com.example.domain.model.moviedetail.MovieDetail

interface MovieDetailDB {
    fun getMovieDetail(movieId: Int): MovieDetail?
    fun saveMovieDetail(movieDetail: MovieDetail)
    fun clearData()
}