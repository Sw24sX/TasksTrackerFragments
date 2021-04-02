package com.example.taskstrackerfragments.ui.home.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R

class RecyclerAdapter(private var tasks: MutableList<Task>, private val onClickListener: OnTaskClickListener) : RecyclerView.Adapter<ViewHolder>() {
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
    }

    fun update(task: Task, position: Int?) {
        if (position != null) tasks.add(position, task)
        else tasks.add(task)
        
    }
}