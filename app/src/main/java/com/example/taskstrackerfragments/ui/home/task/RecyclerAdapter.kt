package com.example.taskstrackerfragments.ui.home.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R
import com.example.data.datatask.Task
import java.lang.NumberFormatException

class RecyclerAdapter(private var tasks: MutableList<com.example.data.datatask.Task>,
                      private val onClickListener: OnTaskClickListener,
                      private val onLongClickListener: OnTaskLongClickListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position], position)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(tasks[position], position)
        }
        holder.itemView.setOnLongClickListener {
            onLongClickListener.onStateLongClick(tasks[position], position)
        }
    }

    fun updateTask(task: com.example.data.datatask.Task, position: Int?) {
        if (position != null && position >= 0 && position < tasks.count()) {
            tasks[position] = task
            notifyItemChanged(position)
        }
        else {
            tasks.add(task)
            notifyItemChanged(tasks.count())
        }
    }

    fun deleteTask(position: Int) {
        tasks.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateListTasks(filteredTasks: MutableList<com.example.data.datatask.Task>){
        tasks = filteredTasks
        notifyDataSetChanged()
    }
}

