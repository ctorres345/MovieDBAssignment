package com.example.data.remote.impl.moviedetail

import com.example.data.datasources.moviedetail.MovieDetailApi
import com.example.data.remote.MovieServices
import com.example.data.remote.mapper.moviedetail.toDomainModel
import com.example.domain.model.moviedetail.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRemote @Inject constructor(
    private val movieServices: MovieServices
) : MovieDetailApi{

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return movieServices.getMovieDetail(movieId).toDomainModel()
    }
}