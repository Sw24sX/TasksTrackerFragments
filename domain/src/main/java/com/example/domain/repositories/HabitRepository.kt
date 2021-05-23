package com.example.domain.repositories

import com.example.domain.entities.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun getHabits(): Flow<List<Habit>>
    suspend fun pushHabit(habit: Habit): Habit
    suspend fun updateHabit(habit: Habit): Habit
    suspend fun deleteHabit(habit: Habit)
}