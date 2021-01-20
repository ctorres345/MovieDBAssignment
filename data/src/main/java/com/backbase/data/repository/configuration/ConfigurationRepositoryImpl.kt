package com.backbase.data.repository.configuration

import com.backbase.data.datasources.configuration.ConfigurationApi
import com.backbase.data.datasources.configuration.ConfigurationDB
import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.repository.configuration.ConfigurationRepository
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