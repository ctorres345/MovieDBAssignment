package com.backbase.data.remote.mapper.genre

import com.backbase.data.remote.model.GenreDTO
import com.backbase.domain.model.genre.Genre

fun GenreDTO.toDomainModel() : Genre {
    return Genre(
        id = id,
        name = name
    )
}