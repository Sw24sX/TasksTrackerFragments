package com.example.taskstrackerfragments.ui.home.homeviewmodel

import androidx.lifecycle.ViewModel
import com.example.data.datatask.AppDatabase
import com.example.taskstrackerfragments.mappers.HabitLocalMapper
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.DataBaseHost
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val db: AppDatabase): ViewModel() {

    init {
        GlobalScope.launch {
            val mapper = HabitLocalMapper()
            db.taskDao().clearDB()
            val tasks = DataBaseHost.getTasks()
            tasks.forEach {
                val habit = mapper.toHabitLocal(it)
                db.taskDao().insert(habit)
            }
        }
    }
}