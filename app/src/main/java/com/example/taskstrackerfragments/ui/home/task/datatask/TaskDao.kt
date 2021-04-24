package com.example.taskstrackerfragments.ui.home.task.datatask

import androidx.room.*
import java.io.Serializable

@Dao
interface TaskDao: Serializable {
    @Query("SELECT * FROM task")
    fun getAll(): MutableList<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getById(id: Long): Task

    @Query("SELECT * FROM task WHERE type = :type")
    fun getTasksByType(type: String): MutableList<Task>
    @Query("SELECT COUNT(*) FROM task WHERE type = :type")
    fun getCountTasksByType(type: TaskType): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}