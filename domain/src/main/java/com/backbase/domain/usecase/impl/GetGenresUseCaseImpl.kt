package com.backbase.domain.usecase.impl
import com.backbase.domain.executor.DefaultDispatcherProvider
import com.backbase.domain.executor.DispatcherProvider
import com.backbase.domain.model.Try
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.repository.genre.GenreRepository
import com.backbase.domain.usecase.GetGenresUseCase
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
                Try.Failure(Try.FailureCause.Unknown(ex))
            }
        }
    }
}