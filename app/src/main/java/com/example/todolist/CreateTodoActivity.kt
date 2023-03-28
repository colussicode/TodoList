package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.databinding.ActivityCreateTodoBinding
import com.example.todolist.db.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateTodo.setOnClickListener {
            createTodo()
        }
    }

    private fun createTodo() {
        lifecycleScope.launch(Dispatchers.IO) {
            (application as MainApplication).databaseInstance.todoDao().createTodo(
                TodoModel(
                    todoId = 0, todoTitle = binding.edtTextNewTodo.text.toString()
                )
            )
        }
        finish()
    }
}