package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.ui.home.task.datatask.Task
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType

interface OnSaveTask {
    fun saveTask(task: Task, fragment: Fragment, typeTask: TaskType)
}