package com.backbase.domain.repository.genre

import com.backbase.domain.model.genre.Genre

interface GenreRepository {
    suspend fun getGenres(): List<Genre>
}