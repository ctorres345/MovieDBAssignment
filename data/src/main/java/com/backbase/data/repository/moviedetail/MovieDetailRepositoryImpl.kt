package com.backbase.data.repository.moviedetail

import com.backbase.data.datasources.moviedetail.MovieDetailApi
import com.backbase.data.datasources.moviedetail.MovieDetailDB
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailApi: MovieDetailApi,
    private val movieDetailDB: MovieDetailDB
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return movieDetailDB.getMovieDetail(movieId)
            ?: movieDetailApi.getMovieDetail(movieId).also { movieDetailDB.saveMovieDetail(it) }
    }

    override suspend fun clearMovieDetailData() {
        movieDetailDB.clearData()
    }
}