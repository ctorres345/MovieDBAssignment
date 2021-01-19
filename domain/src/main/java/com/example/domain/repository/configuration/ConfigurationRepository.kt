package com.example.domain.repository.configuration

import com.example.domain.model.configuration.Configuration

interface ConfigurationRepository {
    suspend fun getConfiguration(): Configuration
}