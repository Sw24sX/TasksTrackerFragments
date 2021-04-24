package com.example.taskstrackerfragments.ui.home.task.datatask

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    var name: String,
    var description: String,
    var countExecutions: String,
    var period: String,
    var priority: String,
    var priorityPosition: String?,
    var type: String
): Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    companion object {
        fun default(type: TaskType): Task {
            return Task("", "", "", "", "", "", type.toString())
        }
    }
}