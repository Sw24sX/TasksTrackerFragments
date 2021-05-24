package com.example.data.api

import java.io.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataBaseHost: Serializable {
    companion object {
        private const val BASE_URL: String = "https://droid-test-server.doubletapp.ru/api/"

        fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}