package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityCreateTodoBinding
import com.example.todolist.db.TodoModel

class CreateTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTodoBinding
    private lateinit var createTodoViewModel: CreateTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        createTodoViewModel = ViewModelProvider(this, CreateTodoViewModelFactory(
            (application as MainApplication).databaseInstance.todoDao()
        ))[CreateTodoViewModel::class.java]
        setContentView(binding.root)

        binding.buttonCreateTodo.setOnClickListener {
            createTodo()
        }
    }

    private fun createTodo() {
        createTodoViewModel.createTodo(
            TodoModel(
                todoTitle = binding.edtTextNewTodo.text.toString()
            )
        )
        finish()
    }
}