package com.example.domain.usecase

import com.example.domain.model.Try
import com.example.domain.model.movie.MoviePage
import com.example.domain.model.MovieSection

interface GetMoviePageUseCase {
    suspend fun execute(page: Int, section: MovieSection) : Try<MoviePage>
}