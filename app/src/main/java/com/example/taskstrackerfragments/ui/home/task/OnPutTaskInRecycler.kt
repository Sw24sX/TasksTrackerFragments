package com.example.taskstrackerfragments.ui.home.task

import java.io.Serializable

interface OnPutTaskInRecycler: Serializable {
    fun putTaskInRecycler(task: Task, position: Int?)
}