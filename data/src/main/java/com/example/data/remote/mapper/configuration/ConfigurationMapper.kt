package com.example.data.remote.mapper.configuration

import com.example.data.remote.model.ConfigurationDTO
import com.example.data.remote.model.ImageConfigurationDTO
import com.example.domain.model.configuration.Configuration
import com.example.domain.model.configuration.ImageConfiguration

fun ImageConfigurationDTO.toDomainModel() : ImageConfiguration {
    return ImageConfiguration(
        baseUrl = baseURL,
        secureUrl = secureBaseURL,
        backdropSizes = backdropSizes,
        posterSizes = posterSizes
    )
}

fun ConfigurationDTO.toDomainModel() : Configuration {
    return Configuration(
        imageConfiguration = imageConfiguration.toDomainModel()
    )
}