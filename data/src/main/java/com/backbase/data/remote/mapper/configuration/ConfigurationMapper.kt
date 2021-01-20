package com.backbase.data.remote.mapper.configuration

import com.backbase.data.remote.model.ConfigurationDTO
import com.backbase.data.remote.model.ImageConfigurationDTO
import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.model.configuration.ImageConfiguration

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