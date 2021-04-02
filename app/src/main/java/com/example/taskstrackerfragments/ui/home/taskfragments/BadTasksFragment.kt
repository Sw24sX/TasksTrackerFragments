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
import com.example.taskstrackerfragments.ui.home.task.OnTaskClickListener
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task

class BadTasksFragment: Fragment(), OnTaskClickListener {
    companion object {
        fun newInstance(): BadTasksFragment {
            return BadTasksFragment()
        }
    }

    private lateinit var activityContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        val recyclerAdapter = RecyclerAdapter(mutableListOf(Task.default(), Task.default()), this)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerAdapter
        return view
    }

    override fun onStateClick(task: Task, position: Int) {
        val changeTask = activityContext as OnChangeTask
        changeTask.changeTask(task, position, this)
    }

    fun onClickNew(view: View) {
        val createTask = activityContext as OnCreateNewTask
        createTask.createTask(this)
    }
}