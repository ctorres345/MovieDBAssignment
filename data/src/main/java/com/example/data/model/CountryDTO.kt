package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("iso_3166_1") val isoCode: String,
    @SerializedName("name") val name: String
)