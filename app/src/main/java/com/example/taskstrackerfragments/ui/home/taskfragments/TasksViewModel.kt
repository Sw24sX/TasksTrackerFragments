package com.example.taskstrackerfragments.ui.home.taskfragments

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.*
import com.example.taskstrackerfragments.ui.home.task.datatask.Task

class TasksViewModel(model: Model): ViewModel(), OnTaskClickListener, OnPutTaskInRecycler {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: SingleLineEvent<ChangeTaskData> = SingleLineEvent()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()

    val onStateClick: MutableLiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTask: LiveData<Any> = mutableOnAddTask

    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(model, this)

    init {
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

    fun onClickFindTasksByName(view: View) {
        val name = view.findViewById<EditText>(R.id.task_name_find).text.toString()
        recyclerAdapter.filter(name)
    }

    fun onClickSortByTop() {
        recyclerAdapter.sortByTop()
    }

    fun onClickSortByBottom() {
        recyclerAdapter.sortByBottom()
    }
}