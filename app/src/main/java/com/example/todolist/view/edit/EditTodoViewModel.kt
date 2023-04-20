package com.example.todolist.view.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.TodoDAO
import kotlinx.coroutines.launch

class EditTodoViewModel(
    private val dao: TodoDAO
) : ViewModel() {

    fun updateTodo(newTitle: String, id: Int) {
        viewModelScope.launch {
            dao.updateTodo(newTitle, id)
        }
    }
}