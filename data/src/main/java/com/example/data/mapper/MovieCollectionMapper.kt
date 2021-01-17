package com.example.data.mapper

import com.example.data.model.CollectionDTO
import com.example.domain.model.MovieCollection

fun CollectionDTO.toDomainModel() : MovieCollection {
    return MovieCollection(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}