package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenreDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)