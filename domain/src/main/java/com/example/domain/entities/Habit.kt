package com.example.domain.entities

data class Habit(
    val name: String,
    val description: String,
    val priority: Int,
    val countExecute: Int,
    val period: Int,
    val type: Int,
    var date: Int,
    var uid: String,
    var id: Int
)