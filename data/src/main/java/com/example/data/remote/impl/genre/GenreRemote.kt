package com.example.data.remote.impl.genre

import com.example.data.datasources.genre.GenreApi
import com.example.data.remote.MovieServices
import com.example.data.remote.mapper.genre.toDomainModel
import com.example.domain.model.genre.Genre
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