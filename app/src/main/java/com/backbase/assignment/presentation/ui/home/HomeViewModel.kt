package com.backbase.assignment.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.mapper.toBillboardMovie
import com.backbase.assignment.mapper.toPopularMovie
import com.backbase.assignment.presentation.base.BaseViewModel
import com.backbase.domain.model.MovieSection
import com.backbase.domain.model.Try
import com.backbase.domain.usecase.GetMoviePageUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val getMoviePageUseCase: GetMoviePageUseCase
) : BaseViewModel() {
    private var currentPage = 0
    private var maxPage : Int? = null
    private val viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val getViewState: LiveData<HomeViewState> = viewState

    fun getMovies() {
        viewModelScope.launch {
            //Get Billboard Movies first
            val billboardMovies = when(val billboardResult = getMoviePageUseCase.execute(page = 1, section = MovieSection.Billboard)){
                is Try.Failure -> {
                    viewState.postValue(HomeViewState.GetBillboardMoviesError)
                    return@launch
                }
                is Try.Success -> billboardResult.value.results.map { it.toBillboardMovie() }
            }
            //Get Popular Movies
            val popularMovies = when(val popularResult = getMoviePageUseCase.execute(page = 1, section = MovieSection.Popular)){
                is Try.Failure -> {
                    viewState.postValue(HomeViewState.GetPopularMoviesError)
                    return@launch
                }
                is Try.Success -> {
                    if(maxPage == null) maxPage = popularResult.value.totalPages
                    currentPage++
                    popularResult.value.results.map { it.toPopularMovie() }
                }
            }
            //Return both results
            viewState.postValue(
                HomeViewState.GetMoviesSuccess(
                    billboardMovies = billboardMovies,
                    popularMovies = popularMovies
                )
            )
        }
    }

    fun getNextPopularMoviePage() {
        val nextPage = currentPage + 1
        getPopularMoviePage(nextPage)
    }

    private fun getPopularMoviePage(page: Int) {
        if (maxPage != null && page !in 1..maxPage!!) return
        viewModelScope.launch {
            val status = when(val popularResult = getMoviePageUseCase.execute(page = page, section = MovieSection.Popular)){
                is Try.Failure -> HomeViewState.GetPaginatedMoviesError
                is Try.Success -> {
                    if(maxPage == null) maxPage = popularResult.value.totalPages
                    currentPage++
                    HomeViewState.GetPopularMoviesSuccess(popularResult.value.results.map { it.toPopularMovie() })
                }
            }
            viewState.postValue(status)
        }
    }
}