package com.example.taskstrackerfragments.ui.home.task

import com.example.data.datatask.Task

interface OnTaskLongClickListener {
    fun onStateLongClick(task: Task, position: Int): Boolean
}