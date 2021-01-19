package com.backbase.assignment.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.mapper.toBillboardMovie
import com.backbase.assignment.mapper.toPopularMovie
import com.backbase.assignment.presentation.base.BaseViewModel
import com.example.domain.model.MovieSection
import com.example.domain.model.Try
import com.example.domain.usecase.GetMoviePageUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val getMoviePageUseCase: GetMoviePageUseCase
) : BaseViewModel() {
    private var currentPage = 0
    private var maxPage = 0
    private val viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val getViewState: LiveData<HomeViewState> = viewState

    fun getMovies() {
        viewModelScope.launch {
            val billboardResult = getMoviePageUseCase.execute(page = 1, section = MovieSection.Billboard)
            val popularResult = getMoviePageUseCase.execute(page = 1, section = MovieSection.Popular)
            val billboardMovies = when(billboardResult){
                is Try.Failure -> {
                    viewState.postValue(HomeViewState.GetBillboardMoviesError)
                    return@launch
                }
                is Try.Success -> billboardResult.value.results.map { it.toBillboardMovie() }
            }
            val popularMovies = when(popularResult){
                is Try.Failure -> {
                    viewState.postValue(HomeViewState.GetPopularMoviesError)
                    return@launch
                }
                is Try.Success -> {
                    maxPage = popularResult.value.totalPages
                    currentPage++
                    popularResult.value.results.map { it.toPopularMovie() }
                }
            }
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
        if (page !in 1..maxPage) return
        viewModelScope.launch {
            val status = when(val popularResult = getMoviePageUseCase.execute(page = page, section = MovieSection.Popular)){
                is Try.Failure -> HomeViewState.GetPopularMoviesError
                is Try.Success -> {
                    currentPage++
                    HomeViewState.GetPopularMoviesSuccess(popularResult.value.results.map { it.toPopularMovie() })
                }
            }
            viewState.postValue(status)
        }
    }
}