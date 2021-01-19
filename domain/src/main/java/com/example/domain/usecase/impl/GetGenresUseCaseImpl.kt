package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.genre.Genre
import com.example.domain.model.Try
import com.example.domain.repository.genre.GenreRepository
import com.example.domain.usecase.GetGenresUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGenresUseCaseImpl(
    private val genreRepository: GenreRepository,
    private val dispatcherProvider: DispatcherProvider
)  : GetGenresUseCase {
    @Inject constructor(genreRepository: GenreRepository) : this(genreRepository, DefaultDispatcherProvider())

    override suspend fun execute(genreId: Int): Try<List<Genre>> {
        return withContext(dispatcherProvider.io()) {
            try {
                val genre = genreRepository.getGenres()
                Try.Success(genre)
            } catch (ex: Exception) {
                Try.Failure(Try.FailureCause.Unknown)
            }
        }
    }
}