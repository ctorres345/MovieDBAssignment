package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

class MoviePageDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)