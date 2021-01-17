package com.example.data.repository.remote

import com.example.data.mapper.toDomainModel
import com.example.domain.model.Movie
import com.example.domain.model.MovieDetail
import com.example.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRemoteRepository @Inject constructor(
    private val movieServices: MovieServices
) : MovieRepository {

    override suspend fun getBillboard(): List<Movie> {
        return movieServices.getBillboard().results.map { it.toDomainModel() }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return movieServices.getPopularMovies().results.map { it.toDomainModel() }
    }

    override suspend fun getMovieDetail(movieId: Long): MovieDetail {
        return movieServices.getMovieDetail(movieId = movieId).toDomainModel()
    }
}