package com.backbase.data.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class MovieDetailDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genres") val genres: List<GenreDTO>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: BigDecimal?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("vote_average") val voteAverage: BigDecimal,
    @SerializedName("vote_count") val voteCount: Int
)