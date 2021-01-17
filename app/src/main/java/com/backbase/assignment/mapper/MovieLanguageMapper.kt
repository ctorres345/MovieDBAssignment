package com.backbase.assignment.mapper

import com.backbase.assignment.model.MovieLanguageUIModel
import com.example.domain.model.MovieLanguage

fun MovieLanguage.toUIModel() : MovieLanguageUIModel {
    return MovieLanguageUIModel(
        isoCode = isoCode,
        name = name
    )
}