package com.example.taskstrackerfragments.ui.home.taskfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.taskstrackerfragments.ui.home.task.OnTaskClickListener
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task

class TasksViewModel: ViewModel(), OnTaskClickListener, OnPutTaskInRecycler {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: MutableLiveData<ChangeTaskData> = MutableLiveData()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()

    val onStateClick: MutableLiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTAsk: LiveData<Any> = mutableOnAddTask

    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(mutableListOf(Task.default()), this)

    init {
        load()
    }

    private fun load() {
        mutableRecyclerAdapterObserver.value = recyclerAdapter
    }

    override fun onStateClick(task: Task, position: Int) {
        onStateClick.value = ChangeTaskData(task, position)
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        recyclerAdapter.update(task, position)
    }

    fun onClickAddTask() {
        mutableOnAddTask.call()
    }
}