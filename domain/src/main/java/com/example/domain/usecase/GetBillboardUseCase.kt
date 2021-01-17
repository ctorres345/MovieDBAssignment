package com.example.domain.usecase

import com.example.domain.model.result.BillboardResult

interface GetBillboardUseCase {
    suspend fun execute() : BillboardResult
}