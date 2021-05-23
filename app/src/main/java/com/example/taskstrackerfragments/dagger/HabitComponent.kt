package com.example.taskstrackerfragments.dagger

import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.PushHabitUseCase
import com.example.domain.usecases.UpdateHabitUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HabitsModule::class])
interface HabitComponent {
    fun getGetHabitsUseCase(): GetHabitsUseCase
    fun getPushHabitUseCase(): PushHabitUseCase
    fun getUpdateHabitUseCase(): UpdateHabitUseCase
}