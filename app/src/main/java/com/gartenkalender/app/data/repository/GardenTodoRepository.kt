package com.gartenkalender.app.data.repository

import com.gartenkalender.app.data.database.GardenTodoDao
import com.gartenkalender.app.data.model.GardenTodo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GardenTodoRepository @Inject constructor(
    private val todoDao: GardenTodoDao
) {
    fun getAllTodos(): Flow<List<GardenTodo>> = todoDao.getAllTodos()

    fun getActiveTodos(): Flow<List<GardenTodo>> = todoDao.getActiveTodos()

    fun getTodosForPlant(plantId: Long): Flow<List<GardenTodo>> =
        todoDao.getTodosForPlant(plantId)

    suspend fun insert(todo: GardenTodo): Long = todoDao.insert(todo)

    suspend fun update(todo: GardenTodo) = todoDao.update(todo)

    suspend fun delete(todo: GardenTodo) = todoDao.delete(todo)

    suspend fun setCompleted(id: Long, completed: Boolean) = todoDao.setCompleted(id, completed)
}
