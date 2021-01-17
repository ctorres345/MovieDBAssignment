package com.example.data.mapper

import com.example.data.model.CountryDTO
import com.example.domain.model.MovieCountry

fun CountryDTO.toDomainModel() : MovieCountry {
    return MovieCountry(
        isoCode = isoCode,
        name = name
    )
}