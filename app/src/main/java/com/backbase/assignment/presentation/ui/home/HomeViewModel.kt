package com.backbase.assignment.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.mapper.toUIModel
import com.backbase.assignment.presentation.base.BaseViewModel
import com.example.domain.model.result.PopularMoviesResult
import com.example.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {
    private val viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val getViewState: LiveData<HomeViewState> = viewState

    fun getPopularMovies() {
        viewModelScope.launch {
            val state = when(val result = getPopularMoviesUseCase.execute()){
                is PopularMoviesResult.Success -> HomeViewState.GetPopularMoviesSuccess(result.resultList.map { it.toUIModel() })
                is PopularMoviesResult.Error -> HomeViewState.GetPopularMoviesError
            }
            viewState.postValue(state)
        }
    }
}