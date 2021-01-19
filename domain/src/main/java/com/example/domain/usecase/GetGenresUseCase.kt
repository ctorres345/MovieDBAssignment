package com.example.domain.usecase

import com.example.domain.model.genre.Genre
import com.example.domain.model.Try

interface GetGenresUseCase {
    suspend fun execute(genreId: Int) : Try<List<Genre>>
}