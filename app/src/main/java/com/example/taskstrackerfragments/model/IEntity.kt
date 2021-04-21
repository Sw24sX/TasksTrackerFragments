package com.example.taskstrackerfragments.model

import com.example.taskstrackerfragments.ui.home.task.Task

interface IEntity {

    fun add(position: Int, task: Task)
    fun add(task: Task)
    fun getAll(): List<Task>
    fun getTask(position: Int): Task

}