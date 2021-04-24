package com.example.taskstrackerfragments.ui.home.task

import com.example.taskstrackerfragments.ui.home.task.datatask.Task
import java.io.Serializable

interface OnPutTaskInRecycler: Serializable {
    fun putTaskInRecycler(task: Task, position: Int?)
}