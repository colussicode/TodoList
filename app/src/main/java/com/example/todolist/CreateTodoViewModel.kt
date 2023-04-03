package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class CreateTodoViewModelFactory(private val dao: TodoDAO) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = CreateTodoViewModel(dao) as T
}