package com.backbase.domain.usecase.impl

import com.backbase.domain.executor.DefaultDispatcherProvider
import com.backbase.domain.executor.DispatcherProvider
import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.Try
import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.movie.MovieRepository
import com.backbase.domain.usecase.GetMoviePageUseCase
import com.backbase.domain.util.configureMovieImages
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoviePageUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val configurationRepository: ConfigurationRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetMoviePageUseCase {
    @Inject constructor(
        movieRepository: MovieRepository,
        configurationRepository: ConfigurationRepository
    ) : this(movieRepository, configurationRepository, DefaultDispatcherProvider())

    override suspend fun execute(page: Int, section: MovieSection): Try<MoviePage> {
        return withContext(dispatcherProvider.io()) {
            try {
                val imageConfig = configurationRepository.getConfiguration()
                val moviePage = movieRepository.getMoviePageForSection(
                    page = page,
                    section = section
                )
                moviePage.results = moviePage.results.configureMovieImages(imageConfig)
                Try.Success(moviePage)
            } catch (ex: Exception) {
                Try.Failure(Try.FailureCause.Unknown(ex))
            }
        }
    }
}