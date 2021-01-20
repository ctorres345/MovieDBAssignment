package com.example.data.cache.impl.movie

import com.example.data.cache.CacheTimestampHelper
import com.example.data.cache.mapper.movie.toDomainModel
import com.example.data.cache.mapper.movie.toRoomModel
import com.example.data.cache.room.MoviesRoomDatabase
import com.example.data.datasources.moviepage.MoviePageDB
import com.example.domain.model.MovieSection
import com.example.domain.model.movie.MoviePage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCache(
    roomDatabase: MoviesRoomDatabase,
    private val timestampHelper: CacheTimestampHelper
) : MoviePageDB {
    @Inject constructor(roomDatabase: MoviesRoomDatabase) : this(roomDatabase, CacheTimestampHelper())

    private val moviesDao = roomDatabase.moviesDao()
    private fun CacheTimestampHelper.moviePagesRefreshTimestamp(): Long = now() + moviePagesRefreshTime()

    override fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage? {
        val moviePage = moviesDao.getMoviePage(page, section.name, timestampHelper.now()) ?: return null
        val movies = moviesDao.getMoviesFromPage(moviePage.id) ?: return null
        return moviePage.toDomainModel(movies)
    }

    override fun saveMoviePageForSection(moviePage: MoviePage, section: MovieSection) {
        val dbMoviePage = moviePage.toRoomModel(
            sectionName = section.name,
            dueDate = timestampHelper.moviePagesRefreshTimestamp()
        )
        val pageId = moviesDao.insertMoviePage(dbMoviePage).toInt()
        val dbMovies = moviePage.results.map { movie ->
            movie.toRoomModel(pageId = pageId)
        }
        moviesDao.insertMovies(dbMovies)
    }

    override fun clearAllPagesInSection(section: MovieSection) {
        moviesDao.deleteAllPagesInSection(section.name)
    }
}