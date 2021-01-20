package com.example.data.datasources.configuration

import com.example.domain.model.configuration.Configuration

interface ConfigurationDB {
    fun getConfiguration() : Configuration?
    fun saveConfiguration(configuration: Configuration)
}