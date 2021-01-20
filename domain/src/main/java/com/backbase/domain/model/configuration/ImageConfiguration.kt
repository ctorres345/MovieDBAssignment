package com.backbase.domain.model.configuration

data class ImageConfiguration(
    val baseUrl: String,
    val secureUrl: String,
    val backdropSizes: List<String>,
    val posterSizes: List<String>
)