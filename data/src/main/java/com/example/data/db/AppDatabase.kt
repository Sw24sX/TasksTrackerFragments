package com.example.data.db

import androidx.room.Database
import java.io.Serializable

@Database(entities = [Task::class], version = 6)
abstract class AppDatabase: RoomDatabase(), Serializable {
    abstract fun taskDao(): TaskDao
}