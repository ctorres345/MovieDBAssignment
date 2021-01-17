package com.example.data.mapper

import com.example.data.model.LanguageDTO
import com.example.domain.model.MovieLanguage

fun LanguageDTO.toDomainModel() : MovieLanguage {
    return MovieLanguage(
        isoCode = isoCode,
        name = name
    )
}