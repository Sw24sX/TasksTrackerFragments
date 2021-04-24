package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskstrackerfragments.OnSaveTask
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.datatask.Task
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType

class ChangeTaskFragment: Fragment() {
    private lateinit var activityContext: Context

    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var countExecution: TextView
    private lateinit var period: TextView
    private lateinit var priority: Spinner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    private var task: Task? = null
    private lateinit var typeTask: TaskType
    private lateinit var viewModel: ChangeTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            task = it.getSerializable(TASK) as Task?
            typeTask = it.getSerializable(TASK_TYPE) as TaskType
        }
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ChangeTaskViewModel(task, typeTask) as T
            }
        }).get(ChangeTaskViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_task, container, false)

        view.findViewById<Button>(R.id.save).setOnClickListener {
            viewModel.onClickSaveTask(grabUserInput())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (task == null) task = Task.default(typeTask)
        val currentTask = task!!

        initElements(view)

        viewModel.task.observe(viewLifecycleOwner, {
            this.name.text = it.name
            this.description.text = it.description
            this.countExecution.text = it.countExecutions
            this.period.text = it.period
            if(it.priorityPosition != null && it.priorityPosition != "")
                this.priority.setSelection(currentTask.priorityPosition!!.toInt())
        })

        viewModel.onSaveTask.observe(viewLifecycleOwner, {
            (activityContext as OnSaveTask).saveTask(it, this, typeTask)
        })
    }

    private fun initElements(view: View) {
        name = view.findViewById(R.id.createName)
        description = view.findViewById(R.id.createDescription)
        countExecution = view.findViewById(R.id.createCountExecution)
        period = view.findViewById(R.id.createPeriod)
        priority = view.findViewById(R.id.selectPriority)
    }

    private fun grabUserInput(): Task {
        val prioritySelectedItem = priority.selectedItemPosition
        val priorityText = priority.selectedItem.toString()
        val result = Task.default(typeTask)
        result.name = name.text.toString()
        result.description =description.text.toString()
        result.countExecutions = countExecution.text.toString()
        result.period = period.text.toString()
        result.priority = priorityText
        result.priorityPosition = prioritySelectedItem.toString()
        return result
    }

    companion object {
        const val TASK: String = "Task"
        const val TASK_TYPE: String = "task_type"

        fun newInstance(type: TaskType) : ChangeTaskFragment {
            val args = Bundle()
            args.putSerializable(TASK_TYPE, type)
            val result = ChangeTaskFragment()
            result.arguments = args
            return result
        }

        fun newInstance(task: Task, type: TaskType) : ChangeTaskFragment {
            val args = Bundle()
            args.putSerializable(TASK, task)
            args.putSerializable(TASK_TYPE, type)
            val result = ChangeTaskFragment()
            result.arguments = args
            return result
        }
    }
}