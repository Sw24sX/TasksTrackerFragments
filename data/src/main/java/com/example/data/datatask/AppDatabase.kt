package com.example.data.datatask

import androidx.room.Database
import androidx.room.RoomDatabase
import java.io.Serializable

@Database(entities = [Task::class], version = 6)
abstract class AppDatabase: RoomDatabase(), Serializable {
    abstract fun taskDao(): TaskDao
}