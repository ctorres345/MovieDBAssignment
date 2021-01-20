package com.backbase.domain.model.movie

data class MoviePage(
    val page: Int,
    var results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)