package com.backbase.data.remote.impl.movie

import com.backbase.data.datasources.moviepage.MoviePageApi
import com.backbase.data.remote.MovieServices
import com.backbase.data.remote.mapper.movie.toDomainModel
import com.backbase.domain.model.movie.MoviePage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemote @Inject constructor(
    private val movieServices: MovieServices
) : MoviePageApi{

    override suspend fun getBillboardMoviePage(page: Int): MoviePage {
        return movieServices.getBillboardMovies(
            language = "en-US",
            page = page
        ).toDomainModel()
    }

    override suspend fun getPopularMoviePage(page: Int): MoviePage {
        return movieServices.getPopularMovies(
            language = "en-US",
            page = page
        ).toDomainModel()
    }
}