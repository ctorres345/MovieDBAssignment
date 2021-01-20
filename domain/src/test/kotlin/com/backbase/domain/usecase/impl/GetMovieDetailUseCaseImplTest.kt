package com.backbase.domain.usecase.impl

import com.backbase.domain.model.Try
import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import com.backbase.domain.usecase.GetMovieDetailUseCase
import com.backbase.testutils.DomainCoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.backbase.testutils.TestStubs
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
class GetMovieDetailUseCaseImplTest {
    @get:Rule
    var coroutinesTestRule = DomainCoroutineTestRule()
    private lateinit var usecase: GetMovieDetailUseCase
    @MockK
    private lateinit var mockMovieDetailRepository: MovieDetailRepository
    @MockK
    private lateinit var mockConfigurationRepository: ConfigurationRepository
    @MockK
    private lateinit var mockConfiguration : Configuration

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        usecase = GetMovieDetailUseCaseImpl(
            movieDetailRepository = mockMovieDetailRepository,
            configurationRepository = mockConfigurationRepository,
            dispatcherProvider = coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `Given an error retrieving the configuration data, then the use case returns a Failure`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfigurationRepository.getConfiguration() } throws Exception()
        coEvery { mockMovieDetailRepository.getMovieDetail(any()) } returns TestStubs.movieDetail
        val result = usecase.execute(1)
        assertTrue(result is Try.Failure)
    }

    @Test
    fun `Given an error retrieving the movie detail, then the use case returns a Failure`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfiguration.imageConfiguration } returns TestStubs.imageConfig
        coEvery { mockConfigurationRepository.getConfiguration() } returns mockConfiguration
        coEvery { mockMovieDetailRepository.getMovieDetail(any()) } throws Exception()
        val result = usecase.execute(1)
        assertTrue(result is Try.Failure)
    }

    @Test
    fun `Given that all repositories return the expected data, then the use case returns a Success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfiguration.imageConfiguration } returns TestStubs.imageConfig
        coEvery { mockConfigurationRepository.getConfiguration() } returns mockConfiguration
        coEvery { mockMovieDetailRepository.getMovieDetail(any()) } returns TestStubs.movieDetail
        val result = usecase.execute(1)
        assertTrue(result is Try.Success<MovieDetail>)
    }
}