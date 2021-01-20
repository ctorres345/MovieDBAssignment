package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieDetailUIModel
import com.backbase.assignment.presentation.ext.toDate
import com.backbase.assignment.presentation.ext.toReleaseDate
import com.backbase.domain.model.moviedetail.MovieDetail

fun MovieDetail.toUIModel() : MovieDetailUIModel {
    return MovieDetailUIModel(
        id = id,
        genres = genres.map { it.toUIModel() },
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate.toDate()?.toReleaseDate() ?: releaseDate,
        title = title,
        runtime = runtime
    )
}

fun MovieDetailUIModel.getHourAndMinutesRuntime() : Pair<Int, Int>? {
    return runtime?.let {
        val hours: Int = it / 60
        val minutes: Int = it % 60
        Pair(hours, minutes)
    }
}