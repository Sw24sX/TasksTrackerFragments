package com.example.taskstrackerfragments.dagger

import android.app.Application
import com.example.domain.usecases.FillDataBaseUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.PushHabitUseCase
import com.example.domain.usecases.UpdateHabitUseCase
import com.example.taskstrackerfragments.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HabitsModule::class])
interface HabitComponent {
    fun getGetHabitsUseCase(): GetHabitsUseCase
    fun getPushHabitUseCase(): PushHabitUseCase
    fun getUpdateHabitUseCase(): UpdateHabitUseCase
    fun getFillDataBaseUseCase(): FillDataBaseUseCase
}