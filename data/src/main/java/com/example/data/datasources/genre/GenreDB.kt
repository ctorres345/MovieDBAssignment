package com.example.data.datasources.genre

import com.example.domain.model.genre.Genre

interface GenreDB {
    fun getGenres() : List<Genre>?
    fun saveGenres(genres: List<Genre>)
}