package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.data.datatask.Task
import com.example.data.datatask.TaskType

interface OnChangeTask {
    fun changeTask(task: com.example.data.datatask.Task, position: Int, putTaskInRecycler: OnPutTaskInRecycler, typeTask: com.example.data.datatask.TaskType)
}