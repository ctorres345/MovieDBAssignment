package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getBillboard() : List<Movie>
    suspend fun getPopularMovies() : List<Movie>
    suspend fun getMovieDetail(movieId: Long) : MovieDetail
}