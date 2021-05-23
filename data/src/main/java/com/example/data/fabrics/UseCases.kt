package com.example.data.fabrics

import android.content.Context
import com.example.data.api.DroidTestService
import com.example.data.datatask.DataBase
import com.example.data.mappers.HabitLocalMapper
import com.example.data.mappers.HabitRemoteMapper
import com.example.data.repositoriesimpl.HabitRepositoryImpl
import com.example.data.repositoriesimpl.HabitsLocalRepositoryImpl
import com.example.data.repositoriesimpl.HabitsRemoteRepositoryImpl
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.PushHabitUseCase
import com.example.domain.usecases.UpdateHabitUseCase
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.DataBaseHost
import kotlinx.coroutines.Dispatchers

class UseCases(context: Context) {
    val test = 1
    val getHabits: GetHabitsUseCase
    val pushHabit: PushHabitUseCase
    val updateHabit: UpdateHabitUseCase
    val deleteHabit: DeleteHabitUseCase

    init {
        val localHabitsMapper = HabitLocalMapper()
        val remoteHabitsMapper = HabitRemoteMapper()

        val localRepository = HabitsLocalRepositoryImpl(DataBase().getDB(context).taskDao(), localHabitsMapper)

        val retrofit = DataBaseHost.buildRetrofit()
        val remoteRepository = HabitsRemoteRepositoryImpl(retrofit.create(DroidTestService::class.java), remoteHabitsMapper)

        val repository = HabitRepositoryImpl(localRepository, remoteRepository)

        getHabits = GetHabitsUseCase(repository, Dispatchers.IO)
        pushHabit = PushHabitUseCase(repository, Dispatchers.IO)
        updateHabit = UpdateHabitUseCase(repository, Dispatchers.IO)
        deleteHabit = DeleteHabitUseCase(repository, Dispatchers.IO)
    }
}