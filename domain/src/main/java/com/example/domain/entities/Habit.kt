package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class Habit(
    @SerializedName("title") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("priority") val priority: Int,
    @SerializedName("count") val countExecute: Int,
    @SerializedName("frequency") val period: Int,
    @SerializedName("type") val type: Int,
    @SerializedName("date") var date: Int,
    @SerializedName("uid") val uid: String
)
