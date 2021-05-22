package com.example.taskstrackerfragments.presenatation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.db.AppDatabase
import com.example.domain.DataBaseHost
import com.example.taskstrackerfragments.data.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val db: com.example.data.db.AppDatabase): ViewModel() {

    init {
        GlobalScope.launch {
            db.taskDao().clearDB()
            val tasks = DataBaseHost.getTasks()
            tasks.forEach {
                db.taskDao().insert(Task.toTask(it))
            }
        }
    }
}