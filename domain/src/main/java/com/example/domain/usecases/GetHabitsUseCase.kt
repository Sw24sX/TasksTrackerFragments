package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.repositories.HabitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetHabitsUseCase(
    private val repository: HabitRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getHabits(): List<Habit> = withContext(dispatcher) {
        return@withContext repository.getHabits()
    }
}