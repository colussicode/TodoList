package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todos")
    suspend fun getTodos() : List<TodoModel>

    @Insert()
    suspend fun createTodo(todo: TodoModel)

    @Query("DELETE FROM todos WHERE todo_title = :title")
    suspend fun deleteTodo(title: String)

    @Query("UPDATE todos SET todo_title = :newTitle WHERE todo_id = :id")
    suspend fun updateTodo(newTitle: String, id: Int)
}