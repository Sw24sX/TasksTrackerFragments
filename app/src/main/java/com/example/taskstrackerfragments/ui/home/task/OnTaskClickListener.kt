package com.example.taskstrackerfragments.ui.home.task

import com.example.data.datatask.Task

interface OnTaskClickListener {
    fun onStateClick(task: Task, position: Int)
}