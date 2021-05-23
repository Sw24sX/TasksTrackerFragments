package com.example.taskstrackerfragments

import androidx.fragment.app.Fragment
import com.example.data.datatask.Task
import com.example.data.datatask.TaskType

interface OnSaveTask {
    fun saveTask(task: com.example.data.datatask.Task, fragment: Fragment, typeTask: com.example.data.datatask.TaskType)
}