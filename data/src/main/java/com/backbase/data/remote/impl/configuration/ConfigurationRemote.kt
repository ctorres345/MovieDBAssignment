package com.backbase.data.remote.impl.configuration

import com.backbase.data.datasources.configuration.ConfigurationApi
import com.backbase.data.remote.MovieServices
import com.backbase.data.remote.mapper.configuration.toDomainModel
import com.backbase.domain.model.configuration.Configuration
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