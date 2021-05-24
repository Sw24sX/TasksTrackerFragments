package com.example.taskstrackerfragments.mappers

import com.example.taskstrackerfragments.entities.Task
import com.example.domain.entities.Habit
import java.io.IOException

class HabitMapper {
    fun toHabit(task: Task): Habit {
        val name: String = if(task.name.isEmpty()) " " else task.name
        val description: String = if(task.description.isEmpty()) " " else task.description
        val priority: Int = getZeroOrValue(task.priorityPosition)
        val count: Int = getZeroOrValue(task.countExecutions)
        val frequency: Int = getZeroOrValue(task.period)
        val type: Int = if(task.type == "GOOD") 0 else 1
        val date: Int = getZeroOrValue(task.date)
        val uid: String = task.uid ?: ""
        return Habit(name, description, priority, count, frequency, type, date, uid, task.id)
    }

    private fun getZeroOrValue(value: String?): Int {
        return try {
            if(value != null && value != "") value.toInt() else 0
        } catch (ex: IOException) {
            0
        }
    }

    fun toTask(habit: Habit): Task {
        val type = if(habit.type == 0) "GOOD" else "BAD"
        val name = if(habit.name == " ") "" else habit.name
        val description = if(habit.description == " ") "" else habit.description
        val res = Task(
            name, description, habit.countExecute.toString(),
            habit.period.toString(), habit.priority.toString(), type,
            habit.uid, habit.date.toString()
        )
        res.id = habit.id
        return res
    }
}