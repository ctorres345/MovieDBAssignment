package com.backbase.assignment.presentation.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.domain.model.Try
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.usecase.GetMovieDetailUseCase
import com.backbase.testutils.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {
    @MockK
    private lateinit var mockUseCase: GetMovieDetailUseCase
    private lateinit var viewModel : MovieDetailViewModel
    private lateinit var movieDetailStub: MovieDetail

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MovieDetailViewModel(mockUseCase)
        movieDetailStub = MovieDetail(
            id = 464052,
            backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            genres = listOf(
                Genre(14, "Fantasy"),
                Genre(28, "Action"),
                Genre(12, "Adventure")
            ),
            overview = "test",
            posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            releaseDate = "2020-12-16",
            title =  "Wonder Woman 1984",
            runtime = 151,
            voteAverage = BigDecimal(7.1),
        )
    }

    @Test
    fun `Given use case return a success result, then the viewmodel emits a success state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any()) } returns Try.Success(movieDetailStub)
        viewModel.getMovieDetail(1)
        assertTrue(viewModel.getViewState.value is MovieDetailViewState.GetMovieDetailSuccess)
    }

    @Test
    fun `Given use case return a failure result, then the viewmodel emits an error state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any()) } returns Try.Failure(Try.FailureCause.Unknown(Exception()))
        viewModel.getMovieDetail(1)
        assertTrue(viewModel.getViewState.value is MovieDetailViewState.GetMovieDetailError)
    }
}