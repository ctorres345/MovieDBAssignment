package com.backbase.data.datasources.genre

import com.backbase.domain.model.genre.Genre

interface GenreDB {
    fun getGenres() : List<Genre>?
    fun saveGenres(genres: List<Genre>)
}