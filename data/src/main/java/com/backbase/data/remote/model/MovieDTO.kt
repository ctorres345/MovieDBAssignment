package com.backbase.data.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class MovieDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: BigDecimal
)