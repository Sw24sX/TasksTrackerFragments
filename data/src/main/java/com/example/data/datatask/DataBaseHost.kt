package com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel

import com.example.data.datatask.DroidTestService
import com.example.data.datatask.Task
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.io.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataBaseHost: Serializable {
    companion object {
        private const val BASE_URL: String = "https://droid-test-server.doubletapp.ru/api/"

        fun putTask(task: com.example.data.datatask.Task): com.example.data.datatask.Task {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            val habit = Habit.toHabit(task)
            habit.date += 1
            val response = service.putHabit(habit).execute()
            if(response.code() == 200) {
                val habitResponse = response.body()
                task.uid = habitResponse!!.uid
            }
            return task
        }

        fun getTasks(): List<com.example.data.datatask.Task> {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            val habits = service.habits().execute().body() ?: return listOf()
            return habits.map { x -> Habit.toTask(x) }.toList()
        }
    }
}

data class Habit(
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
        fun toTask(habit: Habit): com.example.data.datatask.Task {
            val type = if(habit.type == 0) "GOOD" else "BAD"
            val name = if(habit.name == " ") "" else habit.name
            val description = if(habit.description == " ") "" else habit.description
            return com.example.data.datatask.Task(
                name, description, habit.countExecute.toString(),
                habit.period.toString(), habit.priority.toString(), type,
                habit.uid, habit.date.toString()
            )
        }

        fun toHabit(task: com.example.data.datatask.Task): Habit {
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