package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.repositories.HabitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PushHabitUseCase(
    private val repository: HabitRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun pushHabit(habit: Habit): Habit = withContext(dispatcher) {
        return@withContext repository.pushHabit(habit)
    }
}