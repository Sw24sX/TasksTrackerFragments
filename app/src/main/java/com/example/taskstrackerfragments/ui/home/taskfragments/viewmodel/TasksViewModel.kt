package com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel

import android.view.View
import android.widget.EditText
import androidx.lifecycle.*
import com.example.data.api.DataBaseHost
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.*
import com.example.taskstrackerfragments.entities.Task
import com.example.taskstrackerfragments.dagger.HabitComponent
import com.example.taskstrackerfragments.entities.TaskType
import com.example.taskstrackerfragments.mappers.HabitMapper
import com.example.taskstrackerfragments.ui.home.taskfragments.ChangeTaskData
import com.example.taskstrackerfragments.ui.home.taskfragments.SingleLineEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class TasksViewModel(private val applicationComponent: HabitComponent, type: TaskType,
                     owner: LifecycleOwner):
        ViewModel(), OnTaskClickListener, OnPutTaskInRecycler, OnTaskLongClickListener {
    private val mutableRecyclerAdapterObserver: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    private val mutableOnStateClick: SingleLineEvent<ChangeTaskData> = SingleLineEvent()
    private val mutableOnAddTask: SingleLineEvent<Any> = SingleLineEvent()

    val onStateClick: LiveData<ChangeTaskData> = mutableOnStateClick
    val recyclerAdapterObserver: LiveData<RecyclerAdapter> = mutableRecyclerAdapterObserver
    val onAddTask: LiveData<Any> = mutableOnAddTask

    private var taskListObserver: LiveData<List<Task>> = MutableLiveData()
    private var tasksList: MutableList<Task> = mutableListOf()
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(mutableListOf(),
            this, this)
    private val mapper = HabitMapper()

    init {
        taskListObserver.observe(owner, {
            tasksList = it.toMutableList()
            recyclerAdapter.updateListTasks(tasksList)
        })

        GlobalScope.launch(Dispatchers.IO) {
            val habits = applicationComponent.getGetHabitsUseCase().getHabits(type.toString()).asLiveData()
            GlobalScope.launch(Dispatchers.Main) {
                habits.map { list ->  list.map { mapper.toTask(it) } }.observe(owner, {
                    tasksList = it.toMutableList()
                    recyclerAdapter.updateListTasks(tasksList)
                })
            }
        }

        mutableRecyclerAdapterObserver.value = recyclerAdapter
    }

    override fun onStateClick(task: Task, position: Int) {
        mutableOnStateClick.value = ChangeTaskData(task, position)
    }

    override fun onStateLongClick(task: Task, position: Int): Boolean {
        GlobalScope.launch(Dispatchers.IO) {
            //deleteTask(task)
//            db.taskDao().delete(mapper.toHabitLocal(task))
        }
        recyclerAdapter.deleteTask(position)
        return true
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        GlobalScope.launch(Dispatchers.Default) {
            applicationComponent.getPushHabitUseCase().pushHabit(mapper.toHabit(task))
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