package com.example.data.repositoriesimpl

import com.example.data.api.DroidTestService
import com.example.data.mappers.HabitRemoteMapper
import com.example.data.repositories.HabitsRemoteRepository
import com.example.domain.entities.Habit

class HabitsRemoteRepositoryImpl(
    private val service: DroidTestService,
    private val mapper: HabitRemoteMapper
): HabitsRemoteRepository {
    override suspend fun getHabits(): List<Habit> {
        return service
            .habits()
            .execute()
            .body()!!
            .map { mapper.toHabit(it) }
    }

    override suspend fun pushHabit(habit: Habit): Habit {
        val habitRemote = service
            .putHabit(mapper.toHabitRemote(habit))
            .execute()
            .body()
        return mapper.toHabit(habitRemote!!)
    }

    override suspend fun updateHabit(habit: Habit): Habit {
        habit.date++
        val habitRemote = service
            .putHabit(mapper.toHabitRemote(habit))
            .execute()
            .body()
        return mapper.toHabit(habitRemote!!)
    }

    override suspend fun deleteHabit(habit: Habit) {
        TODO("Not yet implemented")
    }
}