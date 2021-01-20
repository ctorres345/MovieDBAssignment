package com.example.domain.repository.genre

import com.example.domain.model.genre.Genre

interface GenreRepository {
    suspend fun getGenres(): List<Genre>
}