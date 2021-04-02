package com.example.taskstrackerfragments

import com.example.taskstrackerfragments.ui.home.task.Task

interface OnSaveTask {
    fun saveTask(task: Task)
}