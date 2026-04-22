package com.gartenkalender.app.ui.screens.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gartenkalender.app.data.model.GardenTodo
import com.gartenkalender.app.data.repository.GardenTodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: GardenTodoRepository
) : ViewModel() {

    val todos: StateFlow<List<GardenTodo>> = todoRepository.getAllTodos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            todoRepository.insert(GardenTodo(title = title, description = description))
        }
    }

    fun toggleCompleted(todo: GardenTodo) {
        viewModelScope.launch {
            todoRepository.setCompleted(todo.id, !todo.isCompleted)
        }
    }

    fun deleteTodo(todo: GardenTodo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }
}
