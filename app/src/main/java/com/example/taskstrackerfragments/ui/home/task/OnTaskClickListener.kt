package com.example.taskstrackerfragments.ui.home.task

import com.example.taskstrackerfragments.ui.home.task.datatask.Task

interface OnTaskClickListener {
    fun onStateClick(task: Task, position: Int)
}