package com.backbase.data.remote

import com.backbase.data.remote.model.ConfigurationDTO
import com.backbase.data.remote.model.GenreResponseDTO
import com.backbase.data.remote.model.MovieDetailDTO
import com.backbase.data.remote.model.MoviePageDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("movie/now_playing")
    suspend fun getBillboardMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviePageDTO

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviePageDTO

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailDTO

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResponseDTO

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationDTO
}