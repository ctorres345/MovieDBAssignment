package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.result.MovieDetailResult
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCaseImpl(
    private val repository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetMovieDetailUseCase {
    @Inject constructor(repository: MovieRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(movieId: Long): MovieDetailResult {
        return withContext(dispatcherProvider.io()) {
            try {
                val result = repository.getMovieDetail(movieId)
                MovieDetailResult.Success(result)
            } catch (ex: Exception) {
                MovieDetailResult.Error(error = ex)
            }
        }
    }
}