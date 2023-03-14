package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id") val todoId: Int = 0,
    @ColumnInfo(name = "todo_title") val todoTitle: String
)
