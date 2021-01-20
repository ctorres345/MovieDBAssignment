package com.backbase.domain.usecase

import com.backbase.domain.model.Try
import com.backbase.domain.model.moviedetail.MovieDetail


interface GetMovieDetailUseCase {
    suspend fun execute(movieId: Int) : Try<MovieDetail>
}