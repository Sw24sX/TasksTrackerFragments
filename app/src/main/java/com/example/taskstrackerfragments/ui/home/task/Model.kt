package com.example.taskstrackerfragments.ui.home.task

class Model {
    private val tasks: MutableList<Task> = mutableListOf()

    fun getTask(position: Int): Task {
        return tasks[position]
    }

    fun putTask(position: Int, task: Task) {
        tasks.add(position, task)
    }

    fun putTask(task: Task) {
        tasks.add(task)
    }

    fun getElements(): List<Task> {
        return tasks
    }

    fun count(): Int {
        return tasks.count()
    }
}