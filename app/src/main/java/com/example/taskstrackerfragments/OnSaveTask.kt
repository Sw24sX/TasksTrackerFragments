package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.data.datatask.Task

interface OnSaveTask {
    fun saveTask(task: Task, fragment: Fragment, typeTask: com.example.data.datatask.TaskType)
}