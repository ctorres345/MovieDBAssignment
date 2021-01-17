package com.example.data.mapper

import com.example.data.model.GenreDTO
import com.example.domain.model.MovieGenre

fun GenreDTO.toDomainModel() : MovieGenre {
    return MovieGenre(
        id = id,
        name = name
    )
}