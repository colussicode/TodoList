package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityEditTodoBinding

class EditTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTodoBinding
    private lateinit var dao: TodoDAO
    private lateinit var editTodoViewModel: EditTodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = AppDatabase.getInstance(baseContext).todoDao()
        editTodoViewModel = ViewModelProvider(this, EditTodoViewModelFactory(dao))[EditTodoViewModel::class.java]
        getTodoTitle()

        binding.buttonCreateTodo.setOnClickListener {
            changeTodoName()
        }
    }

    private fun getTodoTitle() {
        binding.edtTextEditTodo.setText(intent.extras?.getString("todoTitle"))
    }

    private fun changeTodoName() {
        val newTitle = binding.edtTextEditTodo.text.toString()
        val todoId = intent.extras?.getInt("todoId")!!
        editTodoViewModel.updateTodo(newTitle, todoId)
        finish()
    }
}