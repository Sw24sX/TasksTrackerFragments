package com.example.taskstrackerfragments.ui.home.task.datatask

import android.content.Context
import androidx.room.Room

class DataBase {
    fun getDB(context: Context): AppDatabase {
        if(instance != null)
            return instance!!
        instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "AppDataBase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        return instance!!
    }

    companion object {
        private var instance: AppDatabase? = null
    }
}