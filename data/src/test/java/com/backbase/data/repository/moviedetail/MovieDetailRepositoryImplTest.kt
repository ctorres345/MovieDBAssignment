package com.backbase.data.repository.moviedetail

import com.backbase.data.datasources.moviedetail.MovieDetailApi
import com.backbase.data.datasources.moviedetail.MovieDetailDB
import com.backbase.domain.model.moviedetail.MovieDetail
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieDetailRepositoryImplTest {
    private lateinit var repository: MovieDetailRepository
    @MockK
    private lateinit var mockMovieDetailApi: MovieDetailApi
    @MockK
    private lateinit var mockMovieDetailDB: MovieDetailDB
    @MockK
    private lateinit var mockMovieDetail: MovieDetail

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = MovieDetailRepositoryImpl(
            movieDetailApi = mockMovieDetailApi,
            movieDetailDB = mockMovieDetailDB
        )
    }

    @Test
    fun `Given the db return data, then the repository does not call the api`() = runBlocking {
        every { mockMovieDetailDB.getMovieDetail(any()) } returns mockMovieDetail
        repository.getMovieDetail(1)
        verify(exactly = 1) { mockMovieDetailDB.getMovieDetail(1) }
        coVerify(exactly = 0) { mockMovieDetailApi.getMovieDetail(1) }
    }

    @Test
    fun `Given the db does not return data, then the repository call the api`() = runBlocking {
        every { mockMovieDetailDB.getMovieDetail(any()) } returns null
        coEvery { mockMovieDetailApi.getMovieDetail(any()) } returns mockMovieDetail
        repository.getMovieDetail(1)
        verify(exactly = 1) { mockMovieDetailDB.getMovieDetail(1) }
        coVerify(exactly = 1) { mockMovieDetailApi.getMovieDetail(1) }
    }

    @Test
    fun `Given the repository clear data function is called, then the db clears data`() = runBlocking {
        repository.clearMovieDetailData()
        verify(exactly = 1) { mockMovieDetailDB.clearData() }
    }
}