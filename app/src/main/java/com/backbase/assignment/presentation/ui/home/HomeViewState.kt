package com.backbase.assignment.presentation.ui.home

import com.backbase.assignment.model.BillboardMovieUIModel
import com.backbase.assignment.model.PopularMovieUIModel

sealed class HomeViewState {
    data class GetMoviesSuccess(
        val billboardMovies:List<BillboardMovieUIModel>,
        val popularMovies: List<PopularMovieUIModel>
    ) : HomeViewState()
    data class GetPopularMoviesSuccess(val movies: List<PopularMovieUIModel>) : HomeViewState()
    object GetBillboardMoviesError : HomeViewState()
    object GetPopularMoviesError : HomeViewState()
}