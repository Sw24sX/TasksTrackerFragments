package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.data.datatask.TaskType

interface OnCreateNewTask {
    fun createTask(fragment: OnPutTaskInRecycler, typeTask: com.example.data.datatask.TaskType)
}