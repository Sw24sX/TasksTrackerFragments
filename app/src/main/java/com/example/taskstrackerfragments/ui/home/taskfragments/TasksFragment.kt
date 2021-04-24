package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.OnChangeTask
import com.example.taskstrackerfragments.OnCreateNewTask
import com.example.taskstrackerfragments.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.taskstrackerfragments.ui.home.task.Model
import com.example.taskstrackerfragments.ui.home.task.datatask.AppDatabase
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType


class TasksFragment: Fragment() {
    companion object {
        fun newInstance(typeTask: TaskType, db: AppDatabase): TasksFragment {
            val args = Bundle()
            args.putSerializable(TYPE_TASK, typeTask)
            val result = TasksFragment()
            result.arguments = args
            return result
        }

        const val TYPE_TASK: String = "type task"
    }

    private lateinit var activityContext: Context
    lateinit var viewModel: TasksViewModel
    private lateinit var typeTasks: TaskType


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TasksViewModel(Model()) as T
            }
        }).get(TasksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            typeTasks = it.getSerializable(TYPE_TASK) as TaskType
        }

        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recyclerAdapterObserver.observe(viewLifecycleOwner, {
            view.findViewById<RecyclerView>(R.id.recyclerView).adapter = it
        })

        viewModel.onStateClick.observe(viewLifecycleOwner, {
            val changeTask = activityContext as OnChangeTask
            changeTask.changeTask(it.task, it.position, viewModel, typeTasks)
        })

        viewModel.onAddTask.observe(viewLifecycleOwner, {
            val createTask = activityContext as OnCreateNewTask
            createTask.createTask(viewModel, typeTasks)
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