package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityCreateTodoBinding

class CreateTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTodoBinding
    private lateinit var dao: TodoDAO
    private lateinit var createTodoViewModel: CreateTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDatabase.getInstance(applicationContext).todoDao()
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        createTodoViewModel = ViewModelProvider(this, CreateTodoViewModelFactory(dao))[CreateTodoViewModel::class.java]
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