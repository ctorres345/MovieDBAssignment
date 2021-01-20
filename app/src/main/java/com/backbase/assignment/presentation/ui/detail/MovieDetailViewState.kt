package com.backbase.assignment.presentation.ui.detail

import com.backbase.assignment.model.MovieDetailUIModel

sealed class MovieDetailViewState {
    data class GetMovieDetailSuccess(val detail: MovieDetailUIModel) : MovieDetailViewState()
    object GetMovieDetailError : MovieDetailViewState()
}