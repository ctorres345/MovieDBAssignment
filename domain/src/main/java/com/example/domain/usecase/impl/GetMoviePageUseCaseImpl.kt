package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.Try
import com.example.domain.model.movie.MoviePage
import com.example.domain.model.MovieSection
import com.example.domain.repository.configuration.ConfigurationRepository
import com.example.domain.repository.movie.MovieRepository
import com.example.domain.usecase.GetMoviePageUseCase
import com.example.domain.util.configureMovieImages
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
                val moviePage = movieRepository.getMoviePageForSection(
                    page = page,
                    section = section
                )
                Try.Success(
                    moviePage.copy(
                        results = moviePage.results.configureMovieImages(
                            configurationRepository.getConfiguration()
                        )
                    )
                )
            } catch (ex: Exception) {
                Try.Failure(Try.FailureCause.Unknown)
            }
        }
    }
}