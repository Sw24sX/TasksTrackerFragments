package com.example.data.entities

import com.example.data.datatask.Task
import com.google.gson.annotations.SerializedName
import java.io.IOException

data class HabitRemote(
    @SerializedName("title") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("priority") val priority: Int,
    @SerializedName("count") val countExecute: Int,
    @SerializedName("frequency") val period: Int,
    @SerializedName("type") val type: Int,
    @SerializedName("date") var date: Int,
    @SerializedName("uid") val uid: String
) {
    companion object {
        fun toTask(habitRemote: HabitRemote): Task {
            val type = if(habitRemote.type == 0) "GOOD" else "BAD"
            val name = if(habitRemote.name == " ") "" else habitRemote.name
            val description = if(habitRemote.description == " ") "" else habitRemote.description
            return Task(
                name, description, habitRemote.countExecute.toString(),
                habitRemote.period.toString(), habitRemote.priority.toString(), type,
                habitRemote.uid, habitRemote.date.toString()
            )
        }

        fun toHabit(task: Task): HabitRemote {
            val name: String = if(task.name.isEmpty()) " " else task.name
            val description: String = if(task.description.isEmpty()) " " else task.description
            val priority: Int = getZeroOrValue(task.priorityPosition)
            val count: Int = getZeroOrValue(task.countExecutions)
            val frequency: Int = getZeroOrValue(task.period)
            val type: Int = if(task.type == "GOOD") 0 else 1
            val date: Int = getZeroOrValue(task.date)
            val uid: String = task.uid ?: ""
            return HabitRemote(name, description, priority, count, frequency, type, date, uid)
        }

        private fun getZeroOrValue(value: String?): Int {
            return try {
                if(value != null && value != "") value.toInt() else 0
            } catch (ex: IOException) {
                0
            }
        }
    }
}