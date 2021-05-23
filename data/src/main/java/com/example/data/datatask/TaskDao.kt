package com.example.data.datatask

import androidx.room.*
import com.example.data.entities.HabitLocal
import kotlinx.coroutines.flow.Flow
import java.io.Serializable

@Dao
interface TaskDao: Serializable {
    @Query("SELECT * FROM habitlocal")
    fun getAll(): Flow<MutableList<HabitLocal>>

    @Query("SELECT * FROM habitlocal WHERE type = :type")
    fun getTasksByType(type: String): Flow<MutableList<HabitLocal>>

    @Query("SELECT COUNT(*) FROM habitlocal WHERE type = :type")
    fun getCountTasksByType(type: String): Flow<Int>

    @Query("DELETE FROM habitlocal")
    fun clearDB()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: HabitLocal)

    @Update
    fun update(task: HabitLocal)

    @Delete
    fun delete(task: HabitLocal)
}