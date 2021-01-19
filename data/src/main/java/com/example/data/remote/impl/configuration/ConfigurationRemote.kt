package com.example.data.remote.impl.configuration

import com.example.data.datasources.configuration.ConfigurationApi
import com.example.data.remote.MovieServices
import com.example.data.remote.mapper.configuration.toDomainModel
import com.example.domain.model.configuration.Configuration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationRemote @Inject constructor(
    private val movieServices: MovieServices
) : ConfigurationApi{

    override suspend fun getConfiguration(): Configuration {
        return movieServices.getConfiguration().toDomainModel()
    }
}