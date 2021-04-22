package com.example.taskstrackerfragments.ui.home.taskfragments

import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.Task

class ChangeTaskViewModel(private var _task: Task?): ViewModel() {
    private val mutableOnSaveTask: MutableLiveData<Task> = MutableLiveData()
    private val mutableTask: MutableLiveData<Task> = MutableLiveData()

    val onSaveTask: LiveData<Task> = mutableOnSaveTask
    val task: LiveData<Task> = mutableTask

    init {
        if (_task == null) _task = Task.default()
        mutableTask.value = _task
    }

    fun onClickSaveTask(fillTask: Task) {
        mutableOnSaveTask.value = fillTask
    }
}