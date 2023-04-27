package com.example.todolist

import android.app.Application
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.todolist.db.AppDatabase
import com.example.todolist.view.create.CreateTodoViewModel
import com.example.todolist.view.edit.EditTodoViewModel
import com.example.todolist.view.todo.TodoViewModel

private const val DB_NAME = "tododb"
class MainApplication : Application() {

    private val databaseInstance : AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
    }

    val viewModelFactory = viewModelFactory {
        addInitializer(TodoViewModel::class) {
            TodoViewModel(databaseInstance.todoDao())
        }
        addInitializer(EditTodoViewModel::class) {
            EditTodoViewModel(databaseInstance.todoDao())
        }
        addInitializer(CreateTodoViewModel::class) {
            CreateTodoViewModel(databaseInstance.todoDao())
        }
    }
}