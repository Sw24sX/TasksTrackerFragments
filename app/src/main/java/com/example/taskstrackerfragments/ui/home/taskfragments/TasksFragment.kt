package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.OnChangeTask
import com.example.taskstrackerfragments.OnCreateNewTask
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.taskstrackerfragments.ui.home.task.OnTaskClickListener
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable



class TasksFragment: Fragment(), OnTaskClickListener, OnPutTaskInRecycler {
    companion object {
        fun newInstance(tasks: MutableList<Task>): TasksFragment {
            var result = TasksFragment()
            var args = Bundle()
            args.putSerializable(TASKS, Tasks(tasks))
            result.arguments = args
            return result
        }

        const val TASKS: String = "tasks"
        const val TAG: String = "tasks fragment"
    }

    class Tasks(val tasks: MutableList<Task>): Serializable

    private lateinit var activityContext: Context
    private lateinit var tasksAdapter: RecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Log.i(TAG, "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tasks = tasksAdapter.getTasks()
        outState.putSerializable(TASKS, tasks)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val tasks = (it.getSerializable(TASKS) as Tasks).tasks
            tasksAdapter = RecyclerAdapter(tasks, this)
        }
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = tasksAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        view.findViewById<FloatingActionButton>(R.id.floating_action_button).setOnClickListener { onClickAdd() }

        if (savedInstanceState != null) {
            val tasks = (savedInstanceState.getSerializable(TASKS) as RecyclerAdapter.TasksList).tasks
            tasksAdapter = RecyclerAdapter(tasks, this)
        }
        return view
    }

    private fun onClickAdd() {
        Log.i(TAG, "onClickAdd")
        val createTask = activityContext as OnCreateNewTask
        createTask.createTask(this)
    }

    override fun onStateClick(task: Task, position: Int) {
        Log.i(TAG, "onStateClick")
        val changeTask = activityContext as OnChangeTask
        changeTask.changeTask(task, position, this)
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        Log.i(TAG, "putTaskInRecycler")
        tasksAdapter.update(task, position)
    }
}