package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.entities.Task
import com.example.taskstrackerfragments.entities.TaskType

interface OnSaveTask {
    fun saveTask(task: Task, fragment: Fragment, typeTask: TaskType)
}