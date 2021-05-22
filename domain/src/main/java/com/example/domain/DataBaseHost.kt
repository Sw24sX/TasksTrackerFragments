package com.example.domain

import com.example.domain.entities.Habit
import java.io.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataBaseHost: Serializable {
    companion object {
        private const val BASE_URL: String = "https://droid-test-server.doubletapp.ru/api/"

        fun putTask(habit: Habit): Habit {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            habit.date += 1
            val response = service.putHabit(habit).execute()
            return response.body()!!
        }

        fun getTasks(): Array<Habit> {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(DroidTestService::class.java)
            val a = service.habits()
            val b = a.execute()
            val c= b.body()
            return c ?: return arrayOf()
        }
    }
}

