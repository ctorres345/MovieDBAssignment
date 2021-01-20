package com.backbase.assignment.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.presentation.ui.detail.MovieDetailViewModel
import com.backbase.assignment.presentation.ui.detail.MovieDetailViewState
import com.backbase.domain.model.Try
import com.backbase.domain.model.genre.Genre
import com.backbase.domain.model.movie.Movie
import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.usecase.GetMovieDetailUseCase
import com.backbase.domain.usecase.GetMoviePageUseCase
import com.backbase.testutils.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @MockK
    private lateinit var mockUseCase: GetMoviePageUseCase
    private lateinit var viewModel : HomeViewModel
    private lateinit var moviePageStub: MoviePage

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = HomeViewModel(mockUseCase)
        moviePageStub = MoviePage(
            page = 1,
            results = listOf(
                Movie(
                    id = 464052,
                    backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                    genreIds = listOf(14, 28, 12),
                    overview = "test",
                    posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    releaseDate = "2020-12-16",
                    title =  "Wonder Woman 1984",
                    voteAverage = BigDecimal(7.1),
                )
            ),
            totalResults = 10000,
            totalPages = 500
        )
    }

    @Test
    fun `Given getMovies is called and use case return a success result, then the viewmodel emits a success state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any(), any()) } returns Try.Success(moviePageStub)
        viewModel.getMovies()
        assertTrue(viewModel.getViewState.value is HomeViewState.GetMoviesSuccess)
    }

    @Test
    fun `Given getMovies is called and use case return a failure result, then the viewmodel emits an error state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any(), any()) } returns Try.Failure(
            Try.FailureCause.Unknown(
                Exception()
            ))
        viewModel.getMovies()
        assertTrue(viewModel.getViewState.value is HomeViewState.GetBillboardMoviesError)
    }

    @Test
    fun `Given getNextPage is called and use case return a success result, then the viewmodel emits a success state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any(), any()) } returns Try.Success(moviePageStub)
        viewModel.getNextPopularMoviePage()
        assertTrue(viewModel.getViewState.value is HomeViewState.GetPopularMoviesSuccess)
    }

    @Test
    fun `Given getNextPage is called and use case return a failure result, then the viewmodel emits an error state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockUseCase.execute(any(), any()) } returns Try.Failure(
            Try.FailureCause.Unknown(
                Exception()
            ))
        viewModel.getNextPopularMoviePage()
        assertTrue(viewModel.getViewState.value is HomeViewState.GetPaginatedMoviesError)
    }
}