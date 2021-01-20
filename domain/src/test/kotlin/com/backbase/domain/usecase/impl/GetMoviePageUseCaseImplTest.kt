package com.backbase.domain.usecase.impl

import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.Try
import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.movie.MovieRepository
import com.backbase.domain.usecase.GetMoviePageUseCase
import com.backbase.testutils.DomainCoroutineTestRule
import com.backbase.testutils.TestStubs
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviePageUseCaseImplTest {
    @get:Rule
    var coroutinesTestRule = DomainCoroutineTestRule()
    private lateinit var usecase: GetMoviePageUseCase
    @MockK
    private lateinit var mockMovieRepository: MovieRepository
    @MockK
    private lateinit var mockConfigurationRepository: ConfigurationRepository
    @MockK
    private lateinit var mockConfiguration : Configuration
    @MockK
    private lateinit var mockMoviePage : MoviePage

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        usecase = GetMoviePageUseCaseImpl(
            movieRepository = mockMovieRepository,
            configurationRepository = mockConfigurationRepository,
            dispatcherProvider = coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `Given an error retrieving the configuration data, then the use case returns a Failure`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfigurationRepository.getConfiguration() } throws Exception()
        coEvery { mockMovieRepository.getMoviePageForSection(any(), any()) } returns mockMoviePage
        val result = usecase.execute(1, MovieSection.Billboard)
        assertTrue(result is Try.Failure)
    }

    @Test
    fun `Given an error retrieving the movie page, then the use case returns a Failure`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfiguration.imageConfiguration } returns TestStubs.imageConfig
        coEvery { mockConfigurationRepository.getConfiguration() } returns mockConfiguration
        coEvery { mockMovieRepository.getMoviePageForSection(any(), any()) } throws Exception()
        val result = usecase.execute(1, MovieSection.Billboard)
        assertTrue(result is Try.Failure)
    }

    @Test
    fun `Given that all repositories return the expected data, then the use case returns a Success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockConfiguration.imageConfiguration } returns TestStubs.imageConfig
        coEvery { mockMoviePage.results } returns listOf(TestStubs.movie)
        coEvery { mockConfigurationRepository.getConfiguration() } returns mockConfiguration
        coEvery { mockMovieRepository.getMoviePageForSection(any(), any()) } returns mockMoviePage
        val result = usecase.execute(1, MovieSection.Billboard)
        assertTrue(result is Try.Success<MoviePage>)
    }
}