package com.backbase.data.cache.impl.genre

import com.backbase.data.cache.CacheTimestampHelper
import com.backbase.data.cache.mapper.genre.toDomainModel
import com.backbase.data.cache.mapper.genre.toRoomModel
import com.backbase.data.cache.room.MoviesRoomDatabase
import com.backbase.data.datasources.genre.GenreDB
import com.backbase.domain.model.genre.Genre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreCache(
    roomDatabase: MoviesRoomDatabase,
    private val timestampHelper: CacheTimestampHelper
) : GenreDB {
    @Inject constructor(roomDatabase: MoviesRoomDatabase) : this(roomDatabase, CacheTimestampHelper())

    private val movieGenresDao = roomDatabase.genresDao()
    private fun CacheTimestampHelper.movieGenreDueDate() = now() + movieGenresRefreshTime()

    override fun getGenres(): List<Genre>? {
        val genres = movieGenresDao.getMovieGenres(timestampHelper.now())
        return genres?.map { it.toDomainModel() }
    }

    override fun saveGenres(genres: List<Genre>) {
        val dbGenres = genres.map { it.toRoomModel(timestampHelper.movieGenreDueDate()) }
        movieGenresDao.saveMovieGenres(dbGenres)
    }
}