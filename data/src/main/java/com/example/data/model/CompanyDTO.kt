package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CompanyDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String,
    @SerializedName("description") val description: String,
    @SerializedName("headquarters") val headquarters: String,
    @SerializedName("homepage") val homepage: String
)