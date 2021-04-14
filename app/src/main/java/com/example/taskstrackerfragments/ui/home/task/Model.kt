package com.example.taskstrackerfragments.ui.home.task

class Model {
    private val tasks: MutableList<Task> = mutableListOf()

    fun getTask(position: Int): Task {
        return tasks[position]
    }

    fun putTask(position: Int, task: Task) {
        tasks.add(position, task)
    }

    fun getElements(): List<Task> {
        return tasks
    }
}