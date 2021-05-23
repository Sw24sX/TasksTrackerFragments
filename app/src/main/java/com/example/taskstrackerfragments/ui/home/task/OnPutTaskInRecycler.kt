package com.example.taskstrackerfragments.ui.home.task

import com.example.data.datatask.Task
import java.io.Serializable

interface OnPutTaskInRecycler: Serializable {
    fun putTaskInRecycler(task: com.example.data.datatask.Task, position: Int?)
}