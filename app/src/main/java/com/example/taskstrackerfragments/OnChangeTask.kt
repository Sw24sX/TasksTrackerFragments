package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.data.datatask.Task

interface OnChangeTask {
    fun changeTask(task: Task, position: Int, putTaskInRecycler: OnPutTaskInRecycler, typeTask: com.example.data.datatask.TaskType)
}