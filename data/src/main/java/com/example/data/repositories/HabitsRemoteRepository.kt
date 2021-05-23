package com.example.data.repositories

import com.example.domain.entities.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRemoteRepository {
    suspend fun getHabits(): List<Habit>
    suspend fun pushHabit(habit: Habit): Habit
    suspend fun updateHabit(habit: Habit): Habit
    suspend fun deleteHabit(habit: Habit)
}