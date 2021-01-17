package com.example.data.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponseDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)