package com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.DataBaseHost
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.*
import com.example.data.db.AppDatabase
import com.example.taskstrackerfragments.data.Task
import com.example.taskstrackerfragments.data.TaskType
import com.example.taskstrackerfragments.ui.home.taskfragments.ChangeTaskData
import com.example.taskstrackerfragments.ui.home.taskfragments.SingleLineEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class TasksViewModel(private val db: com.example.data.db.AppDatabase, type: TaskType,
                     owner: LifecycleOwner):
        ViewModel(), OnTaskClickListener, OnPutTaskInRecycler, OnTaskLongClickListener {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: SingleLineEvent<ChangeTaskData> = SingleLineEvent()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()

    val onStateClick: LiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTask: LiveData<Any> = mutableOnAddTask

    private var tasksList: MutableList<Task> = mutableListOf()
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(mutableListOf(),
            this, this)

    init {
        db.taskDao().getTasksByType(type.toString()).observe(owner, {
            recyclerAdapter.updateListTasks(it)
            tasksList = it
        })
        mutableRecyclerAdapterObserver.value = recyclerAdapter

    }

    override fun onStateClick(task: Task, position: Int) {
        mutableOnStateClick.value = ChangeTaskData(task, position)
    }

    override fun onStateLongClick(task: Task, position: Int): Boolean {
        GlobalScope.launch(Dispatchers.IO) {
            //deleteTask(task)
            db.taskDao().delete(task)
        }
        recyclerAdapter.deleteTask(position)
        return true
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        GlobalScope.launch(Dispatchers.Default) {
            val habit = DataBaseHost.putTask(Task.toHabit(task))
            val taskUid = Task.toTask(habit)
            if (taskUid.id != 0)
                db.taskDao().update(taskUid)
            else
                db.taskDao().insert(taskUid)
        }
        recyclerAdapter.updateTask(task, position)
    }

    fun addTask() {
        mutableOnAddTask.call()
    }

    fun filterTasksByName(view: View) {
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

    fun sortTasksByTop() {
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

    fun sortTasksByBottom() {
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