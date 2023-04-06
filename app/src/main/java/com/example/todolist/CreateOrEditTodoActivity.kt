package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityEditAndCreateTodoBinding
import com.example.todolist.databinding.FragmentCreateTodoBinding

class CreateOrEditTodoActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var editTodoViewModel: EditTodoViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCreateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTodoViewModel = ViewModelProvider(this, EditTodoViewModelFactory(
            (applicationContext as MainApplication).databaseInstance.todoDao()
        ))[EditTodoViewModel::class.java]

//        getTodoTitle()
//
//        binding.buttonCreateTodo.setOnClickListener {
//            changeTodoName()
//        }
    }

//    private fun getTodoTitle() {
//        binding.edtTextEditTodo.setText(intent.extras?.getString("todoTitle"))
//    }
//
//    private fun changeTodoName() {
//        val newTitle = binding.edtTextEditTodo.text.toString()
//        val todoId = intent.extras?.getInt("todoId")!!
//
//        editTodoViewModel.updateTodo(newTitle, todoId)
//        finish()
}