package com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel

import com.example.data.api.DroidTestService
import com.example.data.datatask.Task
import com.example.data.entities.HabitRemote
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.io.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataBaseHost: Serializable {
    companion object {
        private const val BASE_URL: String = "https://droid-test-server.doubletapp.ru/api/"

        fun putTask(task: Task): Task {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            val habit = HabitRemote.toHabit(task)
            habit.date += 1
            val response = service.putHabit(habit).execute()
            if(response.code() == 200) {
                val habitResponse = response.body()
                task.uid = habitResponse!!.uid
            }
            return task
        }

        fun getTasks(): List<Task> {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            val habits = service.habits().execute().body() ?: return listOf()
            return habits.map { x -> HabitRemote.toTask(x) }.toList()
        }
    }
}