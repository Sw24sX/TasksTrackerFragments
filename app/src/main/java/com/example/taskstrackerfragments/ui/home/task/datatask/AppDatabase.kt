package com.example.taskstrackerfragments.ui.home.task.datatask

import androidx.room.Database
import androidx.room.RoomDatabase
import java.io.Serializable

@Database(entities = [Task::class], version = 3)
abstract class AppDatabase: RoomDatabase(), Serializable {
    abstract fun taskDao(): TaskDao
}