package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.ui.home.task.Task

interface OnSaveTask {
    fun saveTask(task: Task, fragment: Fragment)
}