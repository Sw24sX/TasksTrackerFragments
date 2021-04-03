package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
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

class GoodTasksFragment: Fragment(), OnTaskClickListener, OnPutTaskInRecycler {
    companion object {
        fun newInstance() : GoodTasksFragment {
            return GoodTasksFragment()
        }
    }

    private lateinit var activityContext: Context
    private lateinit var tasksAdapter: RecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    //TODO не работает сохранение при повороте экрана
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        view.findViewById<FloatingActionButton>(R.id.floating_action_button).setOnClickListener { onClickAdd() }
        tasksAdapter = RecyclerAdapter(mutableListOf(Task.default(), Task.default()), this)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = tasksAdapter
        return view
    }

    private fun onClickAdd() {
        val createTask = activityContext as OnCreateNewTask
        createTask.createTask(this)
    }

    override fun onStateClick(task: Task, position: Int) {
        val changeTask = activityContext as OnChangeTask
        changeTask.changeTask(task, position, this)
    }

    override fun putTaskInRecycler(task: Task, position: Int?) {
        tasksAdapter.update(task, position)
    }
}