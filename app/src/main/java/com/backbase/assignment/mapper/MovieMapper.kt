package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieUIModel
import com.example.domain.model.Movie

fun Movie.toUIModel() : MovieUIModel {
    return MovieUIModel(
        id = id,
        adult = adult,
        releaseDate = releaseDate,
        title = title,
        duration = "1h"
    )
}