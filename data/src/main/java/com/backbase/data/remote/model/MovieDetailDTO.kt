package com.backbase.data.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class MovieDetailDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genres") val genres: List<GenreDTO>,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("vote_average") val voteAverage: BigDecimal
)