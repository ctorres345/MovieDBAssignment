package com.backbase.data.cache.mapper.genre

import com.backbase.data.cache.room.tables.GenreEntity
import com.backbase.domain.model.genre.Genre

fun GenreEntity.toDomainModel() : Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun Genre.toRoomModel(dueDate: Long) : GenreEntity {
    return GenreEntity(
        id = id,
        name = name,
        dueDate = dueDate
    )
}