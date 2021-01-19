package com.example.data.repository.moviedetail

import com.example.data.datasources.moviedetail.MovieDetailApi
import com.example.data.datasources.moviedetail.MovieDetailDB
import com.example.domain.model.moviedetail.MovieDetail
import com.example.domain.repository.moviedetail.MovieDetailRepository
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