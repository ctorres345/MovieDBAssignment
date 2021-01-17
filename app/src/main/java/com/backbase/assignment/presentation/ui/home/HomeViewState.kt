package com.backbase.assignment.presentation.ui.home

import com.backbase.assignment.model.MovieUIModel

sealed class HomeViewState {
    data class GetPopularMoviesSuccess(val movies: List<MovieUIModel>) : HomeViewState()
    object GetPopularMoviesError : HomeViewState()
}