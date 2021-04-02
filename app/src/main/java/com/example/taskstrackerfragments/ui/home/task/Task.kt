package com.example.taskstrackerfragments.ui.home.task

import java.io.Serializable

class Task(
    var name: String,
    var description: String,
    var countExecutions: String,
    var period: String,
    var priority: String,
    var priorityPosition: String?
): Serializable {
    companion object {

        fun default(): Task {
            return Task("", "", "", "", "", null)
        }
    }
}