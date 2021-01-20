package com.backbase.data.datasources.genre

import com.backbase.domain.model.genre.Genre

interface GenreApi {
    suspend fun getGenres() : List<Genre>
}