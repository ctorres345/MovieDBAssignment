package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieGenreUIModel
import com.example.domain.model.genre.Genre

fun Genre.toUIModel() : MovieGenreUIModel {
    return MovieGenreUIModel(
        id = id,
        name = name
    )
}