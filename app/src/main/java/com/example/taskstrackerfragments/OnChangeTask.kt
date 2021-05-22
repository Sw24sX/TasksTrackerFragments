package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.taskstrackerfragments.data.Task
import com.example.taskstrackerfragments.data.TaskType

interface OnChangeTask {
    fun changeTask(task: Task, position: Int, putTaskInRecycler: OnPutTaskInRecycler, typeTask: TaskType)
}