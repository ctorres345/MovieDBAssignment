package com.backbase.assignment.presentation.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.mapper.toUIModel
import com.backbase.assignment.presentation.base.BaseViewModel
import com.backbase.domain.model.Try
import com.backbase.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): BaseViewModel() {
    private val viewState: MutableLiveData<MovieDetailViewState> = MutableLiveData()
    val getViewState: LiveData<MovieDetailViewState> = viewState

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val state = when(val result = getMovieDetailUseCase.execute(movieId)) {
                is Try.Success -> MovieDetailViewState.GetMovieDetailSuccess(detail = result.value.toUIModel())
                is Try.Failure -> MovieDetailViewState.GetMovieDetailError
            }
            viewState.postValue(state)
        }
    }
}