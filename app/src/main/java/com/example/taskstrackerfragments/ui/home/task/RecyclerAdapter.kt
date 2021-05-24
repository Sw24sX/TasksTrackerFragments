package com.example.taskstrackerfragments.ui.home.task

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.entities.Task

class RecyclerAdapter(private var tasks: MutableList<Task>,
                      private val onClickListener: OnTaskClickListener,
                      private val onLongClickListener: OnTaskLongClickListener,
                      private val onPerformClickListener: OnClickPerformListener) : RecyclerView.Adapter<ViewHolder>() {

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
        holder.itemView.findViewById<Button>(R.id.perform_id).setOnClickListener {
            onPerformClickListener.onPerformClick(tasks[position], position)
        }
    }

    fun updateTask(task: Task, position: Int?) {
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

    fun updateListTasks(filteredTasks: MutableList<Task>){
        tasks = filteredTasks
        notifyDataSetChanged()
    }

    fun perform(task: Task, position: Int) {
        var date = if (task.date.isEmpty()) 0 else task.date.toInt()
        date += 1
        task.date = date.toString()
        notifyItemChanged(position)
    }
}

