package com.backbase.data.datasources.configuration

import com.backbase.domain.model.configuration.Configuration

interface ConfigurationDB {
    fun getConfiguration() : Configuration?
    fun saveConfiguration(configuration: Configuration)
}