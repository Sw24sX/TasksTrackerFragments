package com.example.data.api

import com.example.data.entities.HabitRemote
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface DroidTestService {
    @Headers("Authorization:$TOKEN")
    @GET("habit")
    fun habits(): Call<Array<HabitRemote>>

    @Headers ("Authorization:$TOKEN")
    @PUT("habit")
    fun putHabit(@Body habitRemote: HabitRemote): Call<HabitRemote>

    @Multipart
    @Headers("Authorization:$TOKEN")
    @DELETE("habit")
    fun deleteHabit(@Part("uid") uid: RequestBody)
}

private const val TOKEN: String = "ac2695ab-5b81-4016-b28d-d5254f0e8f25"