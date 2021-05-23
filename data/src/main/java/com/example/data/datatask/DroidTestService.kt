package com.example.data.datatask

import com.example.data.datatask.Task
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.Habit
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface DroidTestService {
    @Headers("Authorization:$TOKEN")
    @GET("habit")
    fun habits(): Call<Array<Habit>>

    @Headers ("Authorization:$TOKEN")
    @PUT("habit")
    fun putHabit(@Body habit: Habit): Call<Habit>

    @Multipart
    @Headers("Authorization:$TOKEN")
    @DELETE("habit")
    fun deleteHabit(@Part("uid") uid: RequestBody)
}

private const val TOKEN: String = "ac2695ab-5b81-4016-b28d-d5254f0e8f25"