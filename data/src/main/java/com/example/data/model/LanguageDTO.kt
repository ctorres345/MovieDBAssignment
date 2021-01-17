package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LanguageDTO(
    @SerializedName("iso_639_1") val isoCode: String,
    @SerializedName("name") val name: String
)