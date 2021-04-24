package com.example.taskstrackerfragments.ui.home.task.datatask

import androidx.lifecycle.LiveData
import androidx.room.*
import java.io.Serializable

@Dao
interface TaskDao: Serializable {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<MutableList<Task>>

    @Query("SELECT * FROM task WHERE type = :type")
    fun getTasksByType(type: String): LiveData<MutableList<Task>>

    @Query("SELECT COUNT(*) FROM task WHERE type = :type")
    fun getCountTasksByType(type: String): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}