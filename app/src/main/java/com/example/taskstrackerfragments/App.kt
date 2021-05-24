package com.example.taskstrackerfragments

import android.app.Application
import com.example.taskstrackerfragments.dagger.ContextModule
import com.example.taskstrackerfragments.dagger.DaggerHabitComponent
import com.example.taskstrackerfragments.dagger.HabitComponent
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: Application() {

    lateinit var applicationComponent: HabitComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerHabitComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        TODO("Not yet implemented")
//    }
}