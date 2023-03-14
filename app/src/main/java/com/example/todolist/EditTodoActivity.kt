package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityEditTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTodoBinding
    private lateinit var dao: TodoDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = AppDatabase.getInstance(baseContext).todoDao()

        getTodoTitle()

        binding.buttonCreateTodo.setOnClickListener {
            changeTodoName()
        }
    }

    private fun getTodoTitle() {
        binding.edtTextEditTodo.setText(intent.extras?.getString("todoTitle") ?: "no found")
    }

    private fun changeTodoName() {
        val newTitle = binding.edtTextEditTodo.text
        val todoId = intent.extras?.getInt("todoId")!!

        CoroutineScope(Dispatchers.IO).launch {
                dao.updateTodo(newTitle.toString(), todoId)
        }

        finish()
    }
}