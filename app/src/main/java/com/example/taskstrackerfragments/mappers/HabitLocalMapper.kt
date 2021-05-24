package com.example.taskstrackerfragments.mappers

import com.example.taskstrackerfragments.entities.Task
import com.example.data.entities.HabitLocal

class HabitLocalMapper {
    fun toTask(habitLocal: HabitLocal): Task {
        return Task(
            habitLocal.name,
            habitLocal.description,
            habitLocal.countExecutions,
            habitLocal.period,
            habitLocal.priorityPosition,
            habitLocal.type,
            habitLocal.uid,
            habitLocal.date
        )
    }

    fun toHabitLocal(task: Task): HabitLocal {
        return HabitLocal(
            task.name,
            task.description,
            task.countExecutions,
            task.period,
            task.priorityPosition,
            task.type,
            task.uid,
            task.date
        )
    }
}