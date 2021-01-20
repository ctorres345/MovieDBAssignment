package com.example.domain.usecase

import com.example.domain.model.moviedetail.MovieDetail
import com.example.domain.model.Try

interface GetMovieDetailUseCase {
    suspend fun execute(movieId: Int) : Try<MovieDetail>
}