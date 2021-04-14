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
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.OnSaveTask
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task
import kotlin.properties.Delegates

class ChangeTaskFragment: Fragment() {
    companion object {
        const val TASK: String = "Task"

        fun newInstance() : ChangeTaskFragment {
            return ChangeTaskFragment()
        }

        fun newInstance(task: Task) : ChangeTaskFragment {
            val args = Bundle()
            args.putSerializable(TASK, task)
            val result = ChangeTaskFragment()
            result.arguments = args
            return result
        }
    }

    private lateinit var activityContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    private var task: Task? = null
    private lateinit var viewModel: ChangeTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo сделть что то с передаваемым таском
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ChangeTaskViewModel(task) as T
            }
        }).get(ChangeTaskViewModel::class.java)
    }

    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var countExecution: TextView
    private lateinit var period: TextView
    private lateinit var priority: Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            task = it.getSerializable(TASK) as Task
        }

        if (task == null) task = Task.default()
        val currentTask = task!!

        initElements(view)

        this.name.text = currentTask.name
        this.description.text = currentTask.description
        this.countExecution.text = currentTask.countExecutions
        this.period.text = currentTask.period
        if(currentTask.priorityPosition != null)
            this.priority.setSelection(currentTask.priorityPosition!!.toInt())
    }

    private fun initElements(view: View) {
        name = view.findViewById(R.id.createName)
        description = view.findViewById(R.id.createDescription)
        countExecution = view.findViewById(R.id.createCountExecution)
        period = view.findViewById(R.id.createPeriod)
        priority = view.findViewById(R.id.selectPriority)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_task, container, false)

        view.findViewById<Button>(R.id.save).setOnClickListener { onClickSave(view) }
        return view
    }

    fun onClickSave(view: View) {
        val prioritySelectedItem = priority.selectedItemPosition
        val priorityText = priority.selectedItem.toString()
        val result = task!!
        result.name = name.text.toString()
        result.description =description.text.toString()
        result.countExecutions = countExecution.text.toString()
        result.period = period.text.toString()
        result.priority = priorityText
        result.priorityPosition = prioritySelectedItem.toString()

        (activityContext as OnSaveTask).saveTask(result, this)
    }
}