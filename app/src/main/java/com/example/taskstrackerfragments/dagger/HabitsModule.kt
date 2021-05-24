package com.example.taskstrackerfragments.dagger

import android.content.Context
import com.example.data.api.DroidTestService
import com.example.data.datatask.DataBase
import com.example.data.datatask.TaskDao
import com.example.data.mappers.HabitLocalMapper
import com.example.data.mappers.HabitRemoteMapper
import com.example.data.repositories.HabitsLocalRepository
import com.example.data.repositories.HabitsRemoteRepository
import com.example.data.repositoriesimpl.HabitRepositoryImpl
import com.example.data.repositoriesimpl.HabitsLocalRepositoryImpl
import com.example.data.repositoriesimpl.HabitsRemoteRepositoryImpl
import com.example.domain.repositories.HabitRepository
import com.example.domain.usecases.FillDataBaseUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.PushHabitUseCase
import com.example.domain.usecases.UpdateHabitUseCase
import com.example.data.api.DataBaseHost
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class HabitsModule() {

    @Provides
    fun provideGetHabitUseCase(repository: HabitRepository): GetHabitsUseCase {
        return GetHabitsUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun providePushHabitUseCase(repository: HabitRepository): PushHabitUseCase {
        return PushHabitUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun provideUpdateHabitUseCase(repository: HabitRepository): UpdateHabitUseCase {
        return UpdateHabitUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun provideFillDataBaseUseCase(repository: HabitRepository): FillDataBaseUseCase {
        return FillDataBaseUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun provideRepository(localRepository: HabitsLocalRepository, remoteRepository: HabitsRemoteRepository): HabitRepository {
        return HabitRepositoryImpl(localRepository, remoteRepository)
    }

    @Provides
    fun provideHabitsLocalRepository(taskDao: TaskDao): HabitsLocalRepository {
        return HabitsLocalRepositoryImpl(taskDao, HabitLocalMapper())
    }

//    @Provides
//    fun provideContext(application: Application): Context {
//        return application.applicationContext
//    }

    @Provides
    @Singleton
    fun provideTaskDao(context: Context): TaskDao {
        return DataBase().getDB(context).taskDao()
    }

    @Provides
    fun provideHabitsRemoteRepository(service: DroidTestService): HabitsRemoteRepository {
        return HabitsRemoteRepositoryImpl(service, HabitRemoteMapper())
    }

    @Provides
    @Singleton
    fun provideDroidTestService(): DroidTestService {
        val retrofit = DataBaseHost.buildRetrofit()
        return retrofit.create(DroidTestService::class.java)
    }
}