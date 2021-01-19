package com.example.data.remote.mapper.genre

import com.example.data.remote.model.GenreDTO
import com.example.domain.model.genre.Genre

fun GenreDTO.toDomainModel() : Genre {
    return Genre(
        id = id,
        name = name
    )
}