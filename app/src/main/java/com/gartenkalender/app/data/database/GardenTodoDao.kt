package com.gartenkalender.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gartenkalender.app.data.model.GardenTodo
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenTodoDao {

    @Query("SELECT * FROM garden_todos ORDER BY dueDate ASC, createdAt DESC")
    fun getAllTodos(): Flow<List<GardenTodo>>

    @Query("SELECT * FROM garden_todos WHERE isCompleted = 0 ORDER BY dueDate ASC")
    fun getActiveTodos(): Flow<List<GardenTodo>>

    @Query("SELECT * FROM garden_todos WHERE plantId = :plantId")
    fun getTodosForPlant(plantId: Long): Flow<List<GardenTodo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: GardenTodo): Long

    @Update
    suspend fun update(todo: GardenTodo)

    @Delete
    suspend fun delete(todo: GardenTodo)

    @Query("UPDATE garden_todos SET isCompleted = :completed WHERE id = :id")
    suspend fun setCompleted(id: Long, completed: Boolean)
}
