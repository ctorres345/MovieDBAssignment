package com.backbase.data.cache.impl.moviedetail

import com.backbase.data.cache.CacheTimestampHelper
import com.backbase.data.cache.mapper.moviedetail.toDomainModel
import com.backbase.data.cache.mapper.moviedetail.toMovieGenreRoomModel
import com.backbase.data.cache.mapper.moviedetail.toRoomModel
import com.backbase.data.cache.room.MoviesRoomDatabase
import com.backbase.data.datasources.moviedetail.MovieDetailDB
import com.backbase.domain.model.moviedetail.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailCache(
    roomDatabase: MoviesRoomDatabase,
    private val timestampHelper: CacheTimestampHelper
) : MovieDetailDB {
    @Inject constructor(roomDatabase: MoviesRoomDatabase) : this(roomDatabase, CacheTimestampHelper())

    private val detailDao = roomDatabase.movieDetailDao()
    private fun CacheTimestampHelper.refreshTimestamp(): Long = now() + movieDetailsRefreshTime()

    override fun getMovieDetail(movieId: Int): MovieDetail? {
        val movieDetail = detailDao.getMovieDetail(movieId, timestampHelper.now()) ?: return null
        val movieGenres = detailDao.getGenresForDetailId(movieDetail.id) ?: return null
        return movieDetail.toDomainModel(movieGenres)
    }

    override fun saveMovieDetail(movieDetail: MovieDetail) {
        val dbMovieDetail = movieDetail.toRoomModel(timestampHelper.refreshTimestamp())
        val dbGenres = movieDetail.genres.map { it.toMovieGenreRoomModel(dbMovieDetail.id) }
        detailDao.insertMovieDetail(dbMovieDetail)
        detailDao.insertMovieGenres(dbGenres)
    }

    override fun clearData() {
        detailDao.deleteAll()
    }
}