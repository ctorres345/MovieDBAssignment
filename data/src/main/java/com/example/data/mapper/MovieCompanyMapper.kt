package com.example.data.mapper

import com.example.data.model.CompanyDTO
import com.example.domain.model.MovieCompany

fun CompanyDTO.toDomainModel() : MovieCompany {
    return MovieCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}