package com.example.data.repositoriesimpl

import com.example.data.repositories.HabitsLocalRepository
import com.example.data.repositories.HabitsRemoteRepository
import com.example.domain.entities.Habit
import com.example.domain.repositories.HabitRepository
import kotlinx.coroutines.flow.Flow

class HabitRepositoryImpl(
    private val habitsLocalRepository: HabitsLocalRepository,
    private val habitsRemoteRepository: HabitsRemoteRepository,
): HabitRepository {
    override suspend fun getHabits(): Flow<List<Habit>> {
        val habits = habitsRemoteRepository.getHabits()
        return habitsLocalRepository.rewriteHabits(habits)
    }

    override suspend fun pushHabit(habit: Habit): Habit {
        val habit = habitsRemoteRepository.pushHabit(habit)
        return habitsLocalRepository.pushHabit(habit)
    }

    override suspend fun updateHabit(habit: Habit): Habit {
        val habit = habitsRemoteRepository.updateHabit(habit)
        return habitsLocalRepository.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitsLocalRepository.deleteHabit(habit)
        habitsRemoteRepository.deleteHabit(habit)
    }
}