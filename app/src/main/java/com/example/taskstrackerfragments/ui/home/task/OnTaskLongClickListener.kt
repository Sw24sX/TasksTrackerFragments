package com.example.taskstrackerfragments.ui.home.task

import com.example.taskstrackerfragments.ui.home.task.datatask.Task

interface OnTaskLongClickListener {
    fun onStateLongClick(task: Task, position: Int): Boolean
}