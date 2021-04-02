package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.ui.home.task.Task

interface OnChangeTask {
    fun changeTask(task: Task, position: Int, fragment: Fragment)
}