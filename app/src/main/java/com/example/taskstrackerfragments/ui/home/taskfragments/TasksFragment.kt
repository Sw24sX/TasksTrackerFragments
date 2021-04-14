package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.OnChangeTask
import com.example.taskstrackerfragments.OnCreateNewTask
import com.example.taskstrackerfragments.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TasksFragment: Fragment() {
    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
    }

    private lateinit var activityContext: Context
    private lateinit var viewModel: TasksViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TasksViewModel() as T
            }
        }).get(TasksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recyclerAdapterObserver.observe(viewLifecycleOwner, {
            view.findViewById<RecyclerView>(R.id.recyclerView).adapter = it
        })
        
        viewModel.onStateClick.observe(viewLifecycleOwner, {
            val changeTask = activityContext as OnChangeTask
            changeTask.changeTask(it.task, it.position, viewModel)
        })

        viewModel.onAddTAsk.observe(viewLifecycleOwner, {
            val createTask = activityContext as OnCreateNewTask
            createTask.createTask(viewModel)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        view.findViewById<FloatingActionButton>(R.id.floating_action_button).setOnClickListener { viewModel.onClickAddTask() }
        return view
    }
}