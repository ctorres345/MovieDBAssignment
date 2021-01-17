package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieCountryUIModel
import com.example.domain.model.MovieCountry

fun MovieCountry.toUIModel() : MovieCountryUIModel {
    return MovieCountryUIModel(
        isoCode = isoCode,
        name = name
    )
}