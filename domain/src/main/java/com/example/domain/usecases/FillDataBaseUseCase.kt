package com.example.domain.usecases

import com.example.domain.repositories.HabitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FillDataBaseUseCase(
    private val repository: HabitRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun fillDataBase() = withContext(dispatcher) {
        repository.fillDataBase()
    }
}