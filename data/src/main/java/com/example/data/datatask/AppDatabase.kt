package com.example.data.datatask

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.HabitLocal
import java.io.Serializable

@Database(entities = [HabitLocal::class], version = 1)
abstract class AppDatabase: RoomDatabase(), Serializable {
    abstract fun taskDao(): TaskDao
}