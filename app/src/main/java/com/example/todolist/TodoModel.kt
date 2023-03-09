package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey
    @ColumnInfo(name = "todo_title") val todoTitle: String
)
