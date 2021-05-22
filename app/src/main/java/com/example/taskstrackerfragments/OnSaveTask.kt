package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.data.Task
import com.example.taskstrackerfragments.data.TaskType

interface OnSaveTask {
    fun saveTask(task: Task, fragment: Fragment, typeTask: TaskType)
}