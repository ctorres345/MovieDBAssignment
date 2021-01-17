package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieCollectionUIModel
import com.example.domain.model.MovieCollection

fun MovieCollection.toUIModel() : MovieCollectionUIModel {
    return MovieCollectionUIModel(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}