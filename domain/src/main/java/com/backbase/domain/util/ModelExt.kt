package com.backbase.domain.util

import com.backbase.domain.model.configuration.Configuration
import com.backbase.domain.model.configuration.ImageConfiguration
import com.backbase.domain.model.movie.Movie
import com.backbase.domain.model.moviedetail.MovieDetail

internal fun String.createUrlForPath(baseUrl: String, size: String): String {
    return this.let {
        StringBuilder()
            .append(baseUrl)
            .append(size)
            .append(it)
            .toString()
    }
}

internal fun Movie.configurePaths(imageConfig: ImageConfiguration): Movie {
    return this.apply {
        posterPath = posterPath?.createUrlForPath(
            imageConfig.secureUrl,
            imageConfig.posterSizes.last()
        ) ?: ""
        backdropPath = backdropPath?.createUrlForPath(
            imageConfig.secureUrl,
            imageConfig.backdropSizes.last()
        ) ?: ""
    }
}

internal fun MovieDetail.configurePaths(imageConfig: ImageConfiguration): MovieDetail {
    return this.apply {
        backdropPath = backdropPath?.createUrlForPath(
            imageConfig.secureUrl,
            imageConfig.backdropSizes.last()
        )
        posterPath = posterPath?.createUrlForPath(
            imageConfig.secureUrl,
            imageConfig.posterSizes.last()
        )
    }
}

internal fun List<Movie>.configureMovieImages(configuration: Configuration?) : List<Movie> {
    val imageConfig = configuration?.imageConfiguration ?: return this
    return toMutableList().map { it.configurePaths(imageConfig) }
}