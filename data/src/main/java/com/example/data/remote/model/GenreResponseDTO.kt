package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResponseDTO(
    @SerializedName("genres") val genres: List<GenreDTO>
)