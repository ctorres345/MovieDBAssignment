package com.backbase.data.remote.impl.moviedetail

import com.backbase.data.datasources.moviedetail.MovieDetailApi
import com.backbase.data.remote.MovieServices
import com.backbase.data.remote.mapper.moviedetail.toDomainModel
import com.backbase.domain.model.moviedetail.MovieDetail
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