package com.example.taskstrackerfragments.ui.home.taskfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.datatask.Task

class ChangeTaskViewModel(private var _task: Task?, typeTask: com.example.data.datatask.TaskType): ViewModel() {
    private val mutableOnSaveTask: MutableLiveData<Task> = MutableLiveData()
    private val mutableTask: MutableLiveData<Task> = MutableLiveData()

    val onSaveTask: LiveData<Task> = mutableOnSaveTask
    val task: LiveData<Task> = mutableTask

    init {
        if (_task == null) _task = Task.default(typeTask)
        mutableTask.value = _task
    }

    fun onClickSaveTask(fillTask: Task) {
        mutableOnSaveTask.value = fillTask
    }
}