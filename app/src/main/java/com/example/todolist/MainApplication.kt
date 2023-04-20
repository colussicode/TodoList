package com.example.todolist

import android.app.Application
import androidx.room.Room
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.TodoDAO
import com.example.todolist.view.create.CreateTodoViewModel
import com.example.todolist.view.edit.EditTodoViewModel
import com.example.todolist.view.todo.TodoViewModel

private const val DB_NAME = "tododb"
class MainApplication : Application() {

    var todoViewModel: TodoViewModel? = null
    var editTodoViewModel: EditTodoViewModel? = null
    var createTodoViewModel: CreateTodoViewModel? = null

    private val databaseInstance : AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
    }

    override fun onCreate() {
        super.onCreate()
        todoViewModel = TodoViewModelFactory(databaseInstance.todoDao()).create()
        editTodoViewModel = EditTodoViewModelFactory(databaseInstance.todoDao()).create()
        createTodoViewModel = CreateTodoViewModelFactory(databaseInstance.todoDao()).create()
    }

    interface Factory<T> {
        fun create() : T
    }

    class TodoViewModelFactory(private val dao: TodoDAO) : Factory<TodoViewModel> {
        override fun create(): TodoViewModel {
            return TodoViewModel(dao)
        }
    }

    class EditTodoViewModelFactory(private val dao: TodoDAO) : Factory<EditTodoViewModel> {
        override fun create(): EditTodoViewModel {
            return EditTodoViewModel(dao)
        }
    }

    class CreateTodoViewModelFactory(private val dao: TodoDAO) : Factory<CreateTodoViewModel> {
        override fun create(): CreateTodoViewModel {
            return CreateTodoViewModel(dao)
        }
    }
}