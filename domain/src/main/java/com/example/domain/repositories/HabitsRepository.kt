package com.example.domain.repositories

import com.example.domain.entities.Habit
import kotlinx.coroutines.flow.Flow


interface HabitsRepository {
    suspend fun getHabits(): Flow<List<Habit>>

    suspend fun pushHabit(habit: Habit)

    suspend fun updateHabit(habit: Habit)
}