package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.databinding.ActivityEditTodoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTodoTitle()

        binding.buttonCreateTodo.setOnClickListener {
            changeTodoName()
        }
    }

    private fun getTodoTitle() {
        binding.edtTextEditTodo.setText(intent.extras?.getString("todoTitle"))
    }

    private fun changeTodoName() {
        val newTitle = binding.edtTextEditTodo.text
        val todoId = intent.extras?.getInt("todoId")!!

        lifecycleScope.launch(Dispatchers.IO) {
            (application as MainApplication).databaseInstance.todoDao()
                .updateTodo(newTitle.toString(), todoId)
        }

        finish()
    }
}