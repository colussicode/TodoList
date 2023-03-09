package com.example.todolist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todos")
    fun getTodos() : List<TodoModel>

    @Insert()
    fun createTodo(todo: TodoModel)

    @Query("DELETE FROM todos WHERE todo_title = :title")
    fun deleteTodo(title: String)
}