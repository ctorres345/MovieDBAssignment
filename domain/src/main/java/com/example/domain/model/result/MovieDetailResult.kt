package com.example.domain.model.result

import com.example.domain.model.MovieDetail

sealed class MovieDetailResult {
    data class Success(val result: MovieDetail) : MovieDetailResult()
    data class Error(val error: Throwable, val errorMessage: String? = null) : MovieDetailResult()
}