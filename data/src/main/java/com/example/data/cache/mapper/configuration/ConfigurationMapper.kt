package com.example.data.cache.mapper.configuration

import com.example.data.cache.room.ImageType
import com.example.data.cache.room.tables.ImageSizeEntity
import com.example.domain.model.configuration.Configuration
import com.example.domain.model.configuration.ImageConfiguration

fun List<ImageSizeEntity>.toDomainModel() : Configuration {
    return Configuration(
        imageConfiguration = ImageConfiguration(
            baseUrl = this[0].baseUrl,
            secureUrl = this[0].secureUrl,
            posterSizes = this
                .filter { it.imageType == ImageType.Poster.id }
                .map { it.size },
            backdropSizes = this
                .filter { it.imageType == ImageType.Backdrop.id }
                .map { it.size }
        )
    )
}

fun Configuration.toRoomModel(dueDate: Long) :List<ImageSizeEntity> {
    val imageSizes = mutableListOf<ImageSizeEntity>()
    val posterSizes = imageConfiguration
        .posterSizes
        .map {
            ImageSizeEntity(
                baseUrl = imageConfiguration.baseUrl,
                secureUrl = imageConfiguration.secureUrl,
                size = it,
                imageType = ImageType.Poster.id,
                dueDate = dueDate
            )
        }
    val backdropSizes = imageConfiguration
        .backdropSizes
        .map {
            ImageSizeEntity(
                baseUrl = imageConfiguration.baseUrl,
                secureUrl = imageConfiguration.secureUrl,
                size = it,
                imageType = ImageType.Backdrop.id,
                dueDate = dueDate
            )
        }

    return imageSizes.apply {
        addAll(posterSizes)
        addAll(backdropSizes)
    }
}