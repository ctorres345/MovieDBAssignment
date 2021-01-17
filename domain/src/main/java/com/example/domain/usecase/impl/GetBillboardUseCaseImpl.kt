package com.example.domain.usecase.impl

import com.example.domain.executor.DefaultDispatcherProvider
import com.example.domain.executor.DispatcherProvider
import com.example.domain.model.result.BillboardResult
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetBillboardUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBillboardUseCaseImpl(
    private val repository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetBillboardUseCase {
    @Inject constructor(repository: MovieRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(): BillboardResult {
        return withContext(dispatcherProvider.io()) {
            try {
                val result = repository.getBillboard()
                BillboardResult.Success(result)
            } catch (ex: Exception) {
                BillboardResult.Error(error = ex)
            }
        }
    }
}