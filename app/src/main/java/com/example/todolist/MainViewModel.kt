package com.example.todolist

import androidx.lifecycle.*
import com.example.todolist.db.TodoDAO
import com.example.todolist.db.TodoModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val dao: TodoDAO
) : ViewModel() {

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
        }

        getTodos()
    }
}

class MainViewModelFactory(private val dao: TodoDAO) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(dao) as T
}