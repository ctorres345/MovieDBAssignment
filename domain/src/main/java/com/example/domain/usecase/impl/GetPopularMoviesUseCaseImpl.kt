package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.result.PopularMoviesResult
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl(
    private val repository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetPopularMoviesUseCase {
    @Inject
    constructor(repository: MovieRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(): PopularMoviesResult {
        return withContext(dispatcherProvider.io()) {
            try {
                val result = repository.getPopularMovies()
                PopularMoviesResult.Success(result)
            } catch (ex: Exception) {
                PopularMoviesResult.Error(error = ex)
            }
        }
    }
}