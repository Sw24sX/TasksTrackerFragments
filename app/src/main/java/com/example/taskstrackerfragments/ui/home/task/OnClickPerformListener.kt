package com.example.taskstrackerfragments.ui.home.task

import com.example.taskstrackerfragments.entities.Task

interface OnClickPerformListener {
    fun onPerformClick(task: Task, position: Int)
}