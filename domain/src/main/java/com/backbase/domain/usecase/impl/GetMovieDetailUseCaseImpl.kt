package com.backbase.domain.usecase.impl

import com.backbase.domain.executor.DefaultDispatcherProvider
import com.backbase.domain.executor.DispatcherProvider
import com.backbase.domain.model.Try
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import com.backbase.domain.usecase.GetMovieDetailUseCase
import com.backbase.domain.util.configurePaths
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
                val detail = movieDetailRepository.getMovieDetail(movieId)
                Try.Success(detail.configurePaths(imageConfig))
            } catch (ex: Exception) {
                Try.Failure(Try.FailureCause.Unknown(ex))
            }
        }
    }
}