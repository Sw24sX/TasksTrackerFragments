package com.example.data.datatask

import java.io.Serializable

data class Task(
    var name: String,
    var description: String,
    var countExecutions: String,
    var period: String,
    var priorityPosition: String?,
    var type: String,
    var uid: String? = null,
    var date: String = "0"
): Serializable {
    var id: Int = 0

    companion object {
        fun default(type: TaskType): Task {
            return Task("", "", "", "", "", type.toString())
        }
    }
}