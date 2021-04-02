package com.example.taskstrackerfragments.ui.home.task

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameText: TextView = itemView.findViewById(R.id.name)
    val description: TextView = itemView.findViewById(R.id.description)
    val countExecution: TextView = itemView.findViewById(R.id.countExecution)
    val period: TextView = itemView.findViewById(R.id.period)
    val type: TextView = itemView.findViewById(R.id.type)
    val priority: TextView = itemView.findViewById(R.id.priority)

    fun bind(task: Task, position: Int) {
        nameText.text = task.name
        description.text = task.description
        countExecution.text = task.countExecutions
        period.text = task.period
        type.text = task.type
        priority.text = task.priority
    }
}