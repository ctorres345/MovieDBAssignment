package com.example.data.repository.genre

import com.example.data.datasources.genre.GenreApi
import com.example.data.datasources.genre.GenreDB
import com.example.domain.model.genre.Genre
import com.example.domain.repository.genre.GenreRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class GenreRepositoryImpl @Inject constructor(
    private val genreApi: GenreApi,
    private val genreDB: GenreDB
) : GenreRepository {

    override suspend fun getGenres(): List<Genre> {
        return genreDB.getGenres() ?: genreApi.getGenres().also { genreDB.saveGenres(it) }
    }
}