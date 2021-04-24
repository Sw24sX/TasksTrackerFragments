package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType

interface OnCreateNewTask {
    fun createTask(fragment: OnPutTaskInRecycler, typeTask: TaskType)
}