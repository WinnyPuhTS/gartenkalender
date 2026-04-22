package com.gartenkalender.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * A user-created garden task or note.
 */
@Entity(tableName = "garden_todos")
data class GardenTodo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val dueDate: LocalDate? = null,
    val isCompleted: Boolean = false,
    /** Optional link to a plant */
    val plantId: Long? = null,
    val createdAt: LocalDate = LocalDate.now()
)
