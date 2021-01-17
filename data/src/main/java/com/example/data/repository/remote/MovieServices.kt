package com.example.data.repository.remote

import com.example.data.model.BillboardResponseDTO
import com.example.data.model.MovieDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieServices {
    @GET("movie/now_playing?language=en-US&page=undefined")
    suspend fun getBillboard(): BillboardResponseDTO

    @GET("movie/popular?&language=en-US&page=1")
    suspend fun getPopularMovies(): BillboardResponseDTO

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Long): MovieDetailDTO
}