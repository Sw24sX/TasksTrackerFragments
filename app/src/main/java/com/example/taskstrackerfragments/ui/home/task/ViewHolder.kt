package com.example.taskstrackerfragments.ui.home.task

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R
import com.example.data.datatask.Task

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameText: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val countExecution: TextView = itemView.findViewById(R.id.countExecution)
    private val period: TextView = itemView.findViewById(R.id.period)
    private val priority: TextView = itemView.findViewById(R.id.priority)

    fun bind(task: com.example.data.datatask.Task, position: Int) {
        nameText.text = task.name
        description.text = task.description
        countExecution.text = task.countExecutions
        period.text = task.period
        val test = listOf("Нет", "Высокий", "Средний", "Низкий")
        priority.text = test[task.priorityPosition!!.toInt()]
    }
}