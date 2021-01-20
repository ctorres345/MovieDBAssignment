package com.backbase.data.repository.configuration

import com.backbase.data.datasources.configuration.ConfigurationApi
import com.backbase.data.datasources.configuration.ConfigurationDB
import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.repository.configuration.ConfigurationRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ConfigurationRepositoryImplTest {
    private lateinit var repository: ConfigurationRepository
    @MockK
    private lateinit var mockConfigurationApi: ConfigurationApi
    @MockK
    private lateinit var mockConfigurationDB: ConfigurationDB
    @MockK
    private lateinit var mockConfiguration: Configuration

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = ConfigurationRepositoryImpl(
            configurationApi = mockConfigurationApi,
            configurationDB = mockConfigurationDB
        )
    }

    @Test
    fun `Given the db return data, then the repository does not call the api`() = runBlocking {
        every { mockConfigurationDB.getConfiguration() } returns mockConfiguration
        repository.getConfiguration()
        verify(exactly = 1) { mockConfigurationDB.getConfiguration() }
        coVerify(exactly = 0) { mockConfigurationApi.getConfiguration() }
    }

    @Test
    fun `Given the db does not return data, then the repository call the api`() = runBlocking {
        every { mockConfigurationDB.getConfiguration() } returns null
        coEvery { mockConfigurationApi.getConfiguration() } returns mockConfiguration
        repository.getConfiguration()
        verify(exactly = 1) { mockConfigurationDB.getConfiguration() }
        coVerify(exactly = 1) { mockConfigurationApi.getConfiguration() }
        verify(exactly = 1) { mockConfigurationDB.saveConfiguration(any()) }
    }
}