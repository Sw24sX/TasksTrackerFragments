package com.example.taskstrackerfragments.ui.home.homeviewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.datatask.AppDatabase
import com.example.data.datatask.Task
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.DataBaseHost
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val db: AppDatabase): ViewModel() {

    init {
        GlobalScope.launch {
            db.taskDao().clearDB()
            val tasks = DataBaseHost.getTasks()
            tasks.forEach {
                db.taskDao().insert(it)
            }
        }
    }
}