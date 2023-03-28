package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todos")
    fun getTodos(): List<TodoModel>

    @Insert
    fun createTodo(todo: TodoModel)

    @Query("DELETE FROM todos WHERE todo_title = :title")
    fun deleteTodo(title: String)

    @Query("UPDATE todos SET todo_title = :newTitle WHERE todo_id = :id")
    fun updateTodo(newTitle: String, id: Int)
}