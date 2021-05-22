package com.example.domain.usecases

import com.example.domain.repositories.HabitsRepository

class GetHabitsUseCase(private val habitsRepository: HabitsRepository) {
    suspend operator fun invoke() = habitsRepository.getHabits()
}