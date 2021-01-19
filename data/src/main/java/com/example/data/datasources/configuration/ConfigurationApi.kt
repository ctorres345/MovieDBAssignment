package com.example.data.datasources.configuration

import com.example.domain.model.configuration.Configuration

interface ConfigurationApi {
    suspend fun getConfiguration() : Configuration
}