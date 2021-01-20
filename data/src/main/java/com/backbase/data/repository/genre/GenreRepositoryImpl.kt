package com.backbase.data.repository.genre

import com.backbase.data.datasources.genre.GenreApi
import com.backbase.data.datasources.genre.GenreDB
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.repository.genre.GenreRepository
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