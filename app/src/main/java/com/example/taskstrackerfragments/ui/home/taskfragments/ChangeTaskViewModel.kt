package com.example.taskstrackerfragments.ui.home.taskfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.datatask.Task
import com.example.data.datatask.TaskType

class ChangeTaskViewModel(private var _task: com.example.data.datatask.Task?, typeTask: com.example.data.datatask.TaskType): ViewModel() {
    private val mutableOnSaveTask: MutableLiveData<com.example.data.datatask.Task> = MutableLiveData()
    private val mutableTask: MutableLiveData<com.example.data.datatask.Task> = MutableLiveData()

    val onSaveTask: LiveData<com.example.data.datatask.Task> = mutableOnSaveTask
    val task: LiveData<com.example.data.datatask.Task> = mutableTask

    init {
        if (_task == null) _task = com.example.data.datatask.Task.default(typeTask)
        mutableTask.value = _task
    }

    fun onClickSaveTask(fillTask: com.example.data.datatask.Task) {
        mutableOnSaveTask.value = fillTask
    }
}