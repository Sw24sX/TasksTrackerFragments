package com.example.data.repositoriesimpl

import com.example.data.datatask.TaskDao
import com.example.data.mappers.HabitLocalMapper
import com.example.data.repositories.HabitsLocalRepository
import com.example.domain.entities.Habit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitsLocalRepositoryImpl(
    private val taskDao: TaskDao,
    private val mapper: HabitLocalMapper
): HabitsLocalRepository {

    override suspend fun writeHabits(habits: List<Habit>) {
        taskDao.clearDB()
        habits.forEach {
            taskDao.insert(mapper.toHabitLocal(it))
        }
    }

    override suspend fun pushHabit(habit: Habit): Habit {
        taskDao.insert(mapper.toHabitLocal(habit))
        return habit
    }

    override suspend fun updateHabit(habit: Habit): Habit {
        taskDao.update(mapper.toHabitLocal(habit))
        return habit
    }

    override suspend fun deleteHabit(habit: Habit) {
        taskDao.delete(mapper.toHabitLocal(habit))
    }

    override suspend fun getHabits(type: String): Flow<List<Habit>> {
        return taskDao.getTasksByType(type).map { list -> list.map { mapper.toHabit(it) } }
    }
}