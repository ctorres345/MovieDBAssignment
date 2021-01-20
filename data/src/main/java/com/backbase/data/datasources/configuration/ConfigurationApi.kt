package com.backbase.data.datasources.configuration

import com.backbase.domain.model.configuration.Configuration

interface ConfigurationApi {
    suspend fun getConfiguration() : Configuration
}