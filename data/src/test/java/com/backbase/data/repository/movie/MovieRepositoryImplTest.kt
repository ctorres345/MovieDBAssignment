package com.backbase.data.repository.movie

import com.backbase.data.datasources.moviedetail.MovieDetailApi
import com.backbase.data.datasources.moviedetail.MovieDetailDB
import com.backbase.data.datasources.moviepage.MoviePageApi
import com.backbase.data.datasources.moviepage.MoviePageDB
import com.backbase.data.repository.moviedetail.MovieDetailRepositoryImpl
import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.movie.MoviePage
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.repository.movie.MovieRepository
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {
    private lateinit var repository: MovieRepository
    @MockK
    private lateinit var mockMovieApi: MoviePageApi
    @MockK
    private lateinit var mockMovieDB: MoviePageDB
    @MockK
    private lateinit var mockMoviePage: MoviePage

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = MovieRepositoryImpl(
            moviePageApi = mockMovieApi,
            moviePageDB = mockMovieDB
        )
    }

    @Test
    fun `Given the db return data, then the repository does not call the api`() = runBlocking {
        every { mockMovieDB.getMoviePageForSection(any(), any()) } returns mockMoviePage
        repository.getMoviePageForSection(1, MovieSection.Billboard)
        verify(exactly = 1) { mockMovieDB.getMoviePageForSection(1, MovieSection.Billboard) }
        coVerify(exactly = 0) { mockMovieApi.getBillboardMoviePage(1) }
    }

    @Test
    fun `Given the db does not return data, then the repository call the api`() = runBlocking {
        every { mockMovieDB.getMoviePageForSection(any(), any()) } returns null
        coEvery { mockMovieApi.getBillboardMoviePage(any()) } returns mockMoviePage
        repository.getMoviePageForSection(1, MovieSection.Billboard)
        verify(exactly = 1) { mockMovieDB.getMoviePageForSection(1, MovieSection.Billboard) }
        coVerify(exactly = 1) { mockMovieApi.getBillboardMoviePage(1) }
        verify(exactly = 1) { mockMovieDB.saveMoviePageForSection(mockMoviePage, MovieSection.Billboard) }
    }

    @Test
    fun `Given the repository clear data function is called, then the db clears data`() = runBlocking {
        repository.clearMoviePagesForSection(MovieSection.Billboard)
        verify(exactly = 1) { mockMovieDB.clearAllPagesInSection(MovieSection.Billboard) }
    }
}