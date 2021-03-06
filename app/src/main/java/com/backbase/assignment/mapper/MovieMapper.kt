package com.backbase.assignment.mapper

import com.backbase.assignment.model.BillboardMovieUIModel
import com.backbase.assignment.model.PopularMovieUIModel
import com.backbase.assignment.presentation.ext.toDate
import com.backbase.assignment.presentation.ext.toReleaseDate
import com.backbase.domain.model.movie.Movie
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

fun Movie.toPopularMovie() : PopularMovieUIModel {
    return PopularMovieUIModel(
        id = id,
        releaseDate = releaseDate.toDate()?.toReleaseDate() ?: releaseDate,
        title = title,
        posterImage = posterPath ?: "",
        roundedPopularity = getRoundedRating(),
        progressPopularity = getProgressRating()
    )
}

fun Movie.toBillboardMovie() : BillboardMovieUIModel {
    return BillboardMovieUIModel(
        posterImage = posterPath ?: ""
    )
}

fun Movie.getProgressRating() : Float {
    return voteAverage.divide(BigDecimal.TEN).setScale(1, RoundingMode.HALF_EVEN).toFloat()
}

fun Movie.getRoundedRating() : Int {
    return voteAverage.toFloat().times(10f).roundToInt()
}