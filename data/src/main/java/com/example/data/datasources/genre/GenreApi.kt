package com.example.data.datasources.genre

import com.example.domain.model.genre.Genre

interface GenreApi {
    suspend fun getGenres() : List<Genre>
}