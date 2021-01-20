package com.backbase.data.remote.impl.genre

import com.backbase.data.datasources.genre.GenreApi
import com.backbase.data.remote.MovieServices
import com.backbase.data.remote.mapper.genre.toDomainModel
import com.backbase.domain.model.genre.Genre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRemote @Inject constructor(
    private val movieServices: MovieServices
) : GenreApi {

    override suspend fun getGenres(): List<Genre> {
        return movieServices.getMovieGenres().genres.map { it.toDomainModel() }
    }
}