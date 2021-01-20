package com.example.data.repository.configuration

import com.example.data.datasources.configuration.ConfigurationApi
import com.example.data.datasources.configuration.ConfigurationDB
import com.example.domain.model.configuration.Configuration
import com.example.domain.repository.configuration.ConfigurationRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationApi: ConfigurationApi,
    private val configurationDB: ConfigurationDB
) : ConfigurationRepository {

    override suspend fun getConfiguration(): Configuration {
        return configurationDB.getConfiguration() ?: configurationApi.getConfiguration()
            .also { configurationDB.saveConfiguration(it) }
    }
}