package com.example.todolist.view.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.TodoDAO
import com.example.todolist.db.TodoModel
import kotlinx.coroutines.launch

class CreateTodoViewModel(
    private val dao: TodoDAO
) : ViewModel() {

    fun createTodo(todo: TodoModel) {
        viewModelScope.launch {
            dao.createTodo(todo)
        }
    }
}