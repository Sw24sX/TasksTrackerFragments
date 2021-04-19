package com.example.taskstrackerfragments.ui.home.task

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R
import java.io.Serializable

class RecyclerAdapter(private var tasks: Model, private val onClickListener: OnTaskClickListener) : RecyclerView.Adapter<ViewHolder>() {
    private var tasksList: List<Task> = tasks.getElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasksList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasksList[position], position)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(tasksList[position], position)
        }
    }

    fun update(task: Task, position: Int?) {
        if (position != null && position >= 0 && position < tasks.count()) {
            tasks.putTask(position, task)
            tasksList = tasks.getElements()
            notifyItemChanged(position)
        }
        else {
            tasks.putTask(task)
            tasksList = tasks.getElements()
            notifyItemChanged(tasks.count())
        }
    }

    fun filter(name: String) {
        if (name.isEmpty()) {
            tasksList = tasks.getElements()
            notifyDataSetChanged()
        }
        else {
            val filterPattern = name.toLowerCase().trim()
            val result = tasksList.filter {
                it.name.toLowerCase().startsWith(filterPattern)
            }
            tasksList = result
            notifyDataSetChanged()
        }
    }

    fun sortByTop() {
        tasksList = tasksList.sortedBy { it.countExecutions.toInt() }
        notifyDataSetChanged()
    }

    fun sortByBottom() {
        tasksList = tasksList.sortedByDescending { it.countExecutions }
        notifyDataSetChanged()
    }
}

