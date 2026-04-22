package com.gartenkalender.app.ui.screens.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gartenkalender.app.data.model.GardenTodo

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel()) {
    val todos by viewModel.todos.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Aufgabe hinzufügen")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                "Meine Aufgaben",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            if (todos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Keine Aufgaben. Tippe + um eine neue hinzuzufügen.")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos, key = { it.id }) { todo ->
                        TodoItem(
                            todo = todo,
                            onToggle = { viewModel.toggleCompleted(todo) },
                            onDelete = { viewModel.deleteTodo(todo) }
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddTodoDialog(
            onDismiss = { showDialog = false },
            onAdd = { title, description ->
                viewModel.addTodo(title, description)
                showDialog = false
            }
        )
    }
}

@Composable
fun TodoItem(todo: GardenTodo, onToggle: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = todo.isCompleted, onCheckedChange = { onToggle() })
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    todo.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else null
                    )
                )
                if (todo.description.isNotBlank()) {
                    Text(todo.description, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Löschen")
            }
        }
    }
}

@Composable
fun AddTodoDialog(onDismiss: () -> Unit, onAdd: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Neue Aufgabe") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Titel") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Beschreibung (optional)") },
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { if (title.isNotBlank()) onAdd(title, description) },
                enabled = title.isNotBlank()
            ) { Text("Hinzufügen") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Abbrechen") }
        }
    )
}
