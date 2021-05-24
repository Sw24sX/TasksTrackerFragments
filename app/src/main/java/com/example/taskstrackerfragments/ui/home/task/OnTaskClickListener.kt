package com.example.taskstrackerfragments.ui.home.task

import com.example.taskstrackerfragments.entities.Task

interface OnTaskClickListener {
    fun onStateClick(task: Task, position: Int)
}