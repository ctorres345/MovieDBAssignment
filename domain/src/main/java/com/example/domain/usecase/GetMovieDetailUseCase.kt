package com.example.domain.usecase

import com.example.domain.model.result.MovieDetailResult

interface GetMovieDetailUseCase {
    suspend fun execute(movieId: Long) : MovieDetailResult
}