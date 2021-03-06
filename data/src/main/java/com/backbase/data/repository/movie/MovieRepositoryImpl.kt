package com.backbase.data.repository.movie

import com.backbase.data.datasources.moviepage.MoviePageApi
import com.backbase.data.datasources.moviepage.MoviePageDB
import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.model.MovieSection
import com.backbase.domain.repository.movie.MovieRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepositoryImpl @Inject constructor(
    private val moviePageApi: MoviePageApi,
    private val moviePageDB: MoviePageDB
) : MovieRepository {

    override suspend fun getMoviePageForSection(page: Int, section: MovieSection): MoviePage {
        return moviePageDB.getMoviePageForSection(page, section)
            ?: getFromApi(page, section).also { moviePageDB.saveMoviePageForSection(it, section) }
    }

    private suspend fun getFromApi(
        page: Int,
        section: MovieSection
    ): MoviePage = with(moviePageApi) {
        when (section) {
            MovieSection.Billboard -> getBillboardMoviePage(page)
            MovieSection.Popular -> getPopularMoviePage(page)
        }
    }

    override suspend fun clearMoviePagesForSection(section: MovieSection) {
        moviePageDB.clearAllPagesInSection(section)
    }
}