package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.repositories.HabitsRepository

class PushHabitUseCase(private val habitsRepository: HabitsRepository) {
    suspend operator fun invoke(habit: Habit) = habitsRepository.pushHabit(habit)
}