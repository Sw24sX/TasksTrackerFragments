package com.example.taskstrackerfragments.ui.home.taskfragments

import android.view.View
import android.widget.EditText
import androidx.lifecycle.*
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.*
import com.example.taskstrackerfragments.ui.home.task.datatask.AppDatabase
import com.example.taskstrackerfragments.ui.home.task.datatask.Task
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType
import java.lang.NumberFormatException

class TasksViewModel(private val db: AppDatabase, private val type: TaskType, owner: LifecycleOwner): ViewModel(), OnTaskClickListener, OnPutTaskInRecycler {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: SingleLineEvent<ChangeTaskData> = SingleLineEvent()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(mutableListOf(), this)
    private var countTasks: Int = 0
    private var tasksList: MutableList<Task> = mutableListOf()

    val onStateClick: LiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTask: LiveData<Any> = mutableOnAddTask


    init {
        db.taskDao().getCountTasksByType(type.toString()).observe(owner, {
            countTasks = it
        })
        db.taskDao().getTasksByType(type.toString()).observe(owner, {
            recyclerAdapter.updateListTasks(it)
            tasksList = it
        })
        mutableRecyclerAdapterObserver.value = recyclerAdapter
    }

    override fun onStateClick(task: Task, position: Int) {
        mutableOnStateClick.value = ChangeTaskData(task, position)
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        if (task.id != 0)
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
            tasksList
        } else {
            val filterPattern = name.toLowerCase().trim()
            tasksList.filter {
                it.name.toLowerCase().startsWith(filterPattern)
            }.toMutableList()
        }

        recyclerAdapter.updateListTasks(tasks)
    }

    fun onClickSortByTop() {
        val tasks = tasksList.sortedBy {
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
        val tasks = tasksList.sortedByDescending {
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