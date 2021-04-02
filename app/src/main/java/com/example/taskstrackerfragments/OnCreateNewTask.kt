package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.ui.home.task.Task

interface OnCreateNewTask {
    fun createTask(fragment: Fragment)
}