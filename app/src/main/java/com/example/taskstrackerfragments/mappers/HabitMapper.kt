package com.example.taskstrackerfragments.mappers

import com.example.data.datatask.Task
import com.example.data.entities.HabitLocal
import com.example.domain.entities.Habit
import java.io.IOException

class HabitMapper {
    fun toHabit(habitLocal: Task): Habit {
        val name: String = if(habitLocal.name.isEmpty()) " " else habitLocal.name
        val description: String = if(habitLocal.description.isEmpty()) " " else habitLocal.description
        val priority: Int = getZeroOrValue(habitLocal.priorityPosition)
        val count: Int = getZeroOrValue(habitLocal.countExecutions)
        val frequency: Int = getZeroOrValue(habitLocal.period)
        val type: Int = if(habitLocal.type == "GOOD") 0 else 1
        val date: Int = getZeroOrValue(habitLocal.date)
        val uid: String = habitLocal.uid ?: ""
        return Habit(name, description, priority, count, frequency, type, date, uid)
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
        return Task(
            name, description, habit.countExecute.toString(),
            habit.period.toString(), habit.priority.toString(), type,
            habit.uid, habit.date.toString()
        )
    }
}