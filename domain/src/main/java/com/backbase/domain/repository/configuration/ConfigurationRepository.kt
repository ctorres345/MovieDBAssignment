package com.backbase.domain.repository.configuration

import com.backbase.domain.model.configuration.Configuration


interface ConfigurationRepository {
    suspend fun getConfiguration(): Configuration
}