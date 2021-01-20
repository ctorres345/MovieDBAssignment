package com.backbase.domain.usecase

import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.Try
import com.backbase.domain.model.movie.MoviePage

interface GetMoviePageUseCase {
    suspend fun execute(page: Int, section: MovieSection) : Try<MoviePage>
}