package com.example.taskstrackerfragments.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.Habit
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.io.Serializable

@Entity
data class Task(
    @SerializedName("title")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("count")
    var countExecutions: String,

    @SerializedName("frequency")
    var period: String,

    @SerializedName("priority")
    var priorityPosition: String?,

    @SerializedName("type")
    var type: String,

    @SerializedName("uid")
    var uid: String? = null,

    @SerializedName("date")
    var date: String = "0"
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun default(type: TaskType): Task {
            return Task("", "", "", "", "", type.toString())
        }

        fun toTask(habit: Habit): Task {
            val type = if(habit.type == 0) "GOOD" else "BAD"
            val name = if(habit.name == " ") "" else habit.name
            val description = if(habit.description == " ") "" else habit.description
            return Task(name, description, habit.countExecute.toString(),
                habit.period.toString(), habit.priority.toString(), type,
                habit.uid, habit.date.toString())
        }

        fun toHabit(task: Task): Habit {
            val name: String = if(task.name.isEmpty()) " " else task.name
            val description: String = if(task.description.isEmpty()) " " else task.description
            val priority: Int = getZeroOrValue(task.priorityPosition)
            val count: Int = getZeroOrValue(task.countExecutions)
            val frequency: Int = getZeroOrValue(task.period)
            val type: Int = if(task.type == "GOOD") 0 else 1
            val date: Int = getZeroOrValue(task.date)
            val uid: String = task.uid ?: ""
            return Habit(name, description, priority, count, frequency, type, date, uid)
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