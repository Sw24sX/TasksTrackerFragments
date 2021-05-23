package com.example.taskstrackerfragments.ui.home.task

import com.example.data.datatask.Task

interface OnTaskClickListener {
    fun onStateClick(task: com.example.data.datatask.Task, position: Int)
}