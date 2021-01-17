package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieCompanyUIModel
import com.example.domain.model.MovieCompany

fun MovieCompany.toUIModel() : MovieCompanyUIModel {
    return MovieCompanyUIModel(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}