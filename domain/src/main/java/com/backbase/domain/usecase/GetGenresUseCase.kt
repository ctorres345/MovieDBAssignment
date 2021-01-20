package com.backbase.domain.usecase

import com.backbase.domain.model.Try
import com.backbase.domain.model.genre.Genre

interface GetGenresUseCase {
    suspend fun execute(genreId: Int) : Try<List<Genre>>
}