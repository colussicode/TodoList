package com.example.todolist.view.todo

import androidx.lifecycle.*
import com.example.todolist.db.TodoDAO
import com.example.todolist.db.TodoModel
import kotlinx.coroutines.launch

class TodoViewModel(private val dao: TodoDAO) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoModel>>()
    val todos: LiveData<List<TodoModel>> = _todos

    fun getTodos() {
        viewModelScope.launch {
            val todos = dao.getTodos()
            _todos.postValue(todos)
        }
    }

    fun deleteTodo(title: String) {
        viewModelScope.launch {
            dao.deleteTodo(title)
            getTodos()
        }
    }
}