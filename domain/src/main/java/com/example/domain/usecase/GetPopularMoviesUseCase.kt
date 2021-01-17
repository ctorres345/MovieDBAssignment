package com.example.domain.usecase

import com.example.domain.model.result.PopularMoviesResult

interface GetPopularMoviesUseCase {
    suspend fun execute() : PopularMoviesResult
}