package com.example.taskstrackerfragments.model

import com.example.taskstrackerfragments.ui.home.task.Task

class Entity: IEntity {

    private var tasks = mutableListOf<Task>()

    override fun add(position: Int, task: Task) {
        tasks.add(position, task)
    }

    override fun add(task: Task) {
        tasks.add(task)
    }

    override fun getAll(): List<Task> {
        return tasks
    }

    override fun getTask(position: Int): Task {
        return tasks[position]
    }
}