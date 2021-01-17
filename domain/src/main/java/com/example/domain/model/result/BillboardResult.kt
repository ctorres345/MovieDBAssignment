package com.example.domain.model.result

import com.example.domain.model.Movie

sealed class BillboardResult {
    data class Success(val resultList: List<Movie>) : BillboardResult()
    data class Error(val error: Throwable, val errorMessage: String? = null) : BillboardResult()
}