package com.example.domain.model.result

import com.example.domain.model.Movie

sealed class PopularMoviesResult {
    data class Success(val resultList: List<Movie>) : PopularMoviesResult()
    data class Error(val error: Throwable, val errorMessage: String? = null) : PopularMoviesResult()
}