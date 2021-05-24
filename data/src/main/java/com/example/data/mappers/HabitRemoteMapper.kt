package com.example.data.mappers

import com.example.data.entities.HabitRemote
import com.example.domain.entities.Habit

class HabitRemoteMapper {
    fun toHabit(habitRemote: HabitRemote): Habit {
        return Habit(
            habitRemote.name,
            habitRemote.description,
            habitRemote.priority,
            habitRemote.countExecute,
            habitRemote.period,
            habitRemote.type,
            habitRemote.date,
            habitRemote.uid,
            habitRemote.id
        )
    }

    fun toHabitRemote(habit: Habit): HabitRemote {
        val result = HabitRemote(
            habit.name,
            habit.description,
            habit.priority,
            habit.countExecute,
            habit.period,
            habit.type,
            habit.date,
            habit.uid
        )
        result.id = habit.id
        return result
    }
}