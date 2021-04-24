package com.example.taskstrackerfragments.ui.home.task.datatask

import androidx.room.Database
import androidx.room.RoomDatabase
import java.io.Serializable

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}