package com.example.taskstrackerfragments.ui.home.task

import com.example.data.datatask.Task

interface OnTaskLongClickListener {
    fun onStateLongClick(task: com.example.data.datatask.Task, position: Int): Boolean
}