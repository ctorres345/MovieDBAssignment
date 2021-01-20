package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.Try
import com.example.domain.model.moviedetail.MovieDetail
import com.example.domain.repository.configuration.ConfigurationRepository
import com.example.domain.repository.moviedetail.MovieDetailRepository
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.util.configurePaths
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCaseImpl(
    private val movieDetailRepository: MovieDetailRepository,
    private val configurationRepository: ConfigurationRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetMovieDetailUseCase {
    @Inject constructor(
        movieDetailRepository: MovieDetailRepository,
        configurationRepository: ConfigurationRepository
    ) : this(movieDetailRepository, configurationRepository, DefaultDispatcherProvider())

    override suspend fun execute(movieId: Int): Try<MovieDetail> {
        return withContext(dispatcherProvider.io()) {
            try {
                val imageConfig = configurationRepository.getConfiguration().imageConfiguration
                movieDetailRepository.getMovieDetail(movieId)?.let {
                    Try.Success(it.configurePaths(imageConfig))
                } ?: Try.Failure(Try.FailureCause.Unknown)
            } catch (ex: Exception) {
                Try.Failure(Try.FailureCause.Unknown)
            }
        }
    }
}