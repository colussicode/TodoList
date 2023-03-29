package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

class EditTodoViewModelFactory(private val dao: TodoDAO) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = EditTodoViewModel(dao) as T
}