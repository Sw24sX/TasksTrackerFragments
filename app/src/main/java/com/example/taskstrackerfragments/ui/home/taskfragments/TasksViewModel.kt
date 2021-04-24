package com.example.taskstrackerfragments.ui.home.taskfragments

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.*
import com.example.taskstrackerfragments.ui.home.task.datatask.AppDatabase
import com.example.taskstrackerfragments.ui.home.task.datatask.Task
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType
import java.lang.NumberFormatException

class TasksViewModel(private val db: AppDatabase, private val type: TaskType): ViewModel(), OnTaskClickListener, OnPutTaskInRecycler {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: SingleLineEvent<ChangeTaskData> = SingleLineEvent()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()

    val onStateClick: MutableLiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTask: LiveData<Any> = mutableOnAddTask

    private val recyclerAdapter: RecyclerAdapter

    init {
        val tasks = db.taskDao().getTasksByType(type.toString())
        recyclerAdapter = RecyclerAdapter(tasks, this)
        mutableRecyclerAdapterObserver.value = recyclerAdapter
    }

    override fun onStateClick(task: Task, position: Int) {
        onStateClick.value = ChangeTaskData(task, position)
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        val tasksCount = db.taskDao().getCountTasksByType(type)
        if (task.id < tasksCount)
            db.taskDao().update(task)
        else
            db.taskDao().insert(task)
        recyclerAdapter.updateTask(task, position)
    }

    fun onClickAddTask() {
        mutableOnAddTask.call()
    }

    fun onClickFindTasksByName(view: View) {
        val name = view.findViewById<EditText>(R.id.task_name_find).text.toString()

        val tasks: MutableList<Task> = if(name.isEmpty()) {
            db.taskDao().getTasksByType(type.toString())
        } else {
            val filterPattern = name.toLowerCase().trim()
            db.taskDao().getTasksByType(type.toString()).filter {
                it.name.toLowerCase().startsWith(filterPattern)
            }.toMutableList()
        }

        recyclerAdapter.updateListTasks(tasks)
    }

    fun onClickSortByTop() {
        val tasks = db.taskDao().getTasksByType(type.toString()).sortedBy {
            try {
                it.countExecutions.toInt()
            }
            catch(ex: NumberFormatException) {
                0
            }
        }.toMutableList()

        recyclerAdapter.updateListTasks(tasks)
    }

    fun onClickSortByBottom() {
        val tasks = db.taskDao().getTasksByType(type.toString()).sortedByDescending {
            try {
                it.countExecutions.toInt()
            }
            catch(ex: NumberFormatException) {
                0
            }
        }.toMutableList()

        recyclerAdapter.updateListTasks(tasks)
    }
}