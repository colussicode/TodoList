package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.TodoItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoItemBiding: TodoItemBinding
    private lateinit var dao: TodoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        todoItemBiding = TodoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = AppDatabase.getInstance(baseContext).todoDao()

        initRv()

        binding.buttonNewTodo.setOnClickListener {
            goToCreateTodoScreen()
        }
    }

    private fun goToCreateTodoScreen() {
        val intent =  Intent(this, CreateTodoActivity::class.java)
        startActivity(intent)
    }

    private fun initRv() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val todos = dao.getTodos()
            binding.rvTasks.layoutManager = LinearLayoutManager(baseContext)
            binding.rvTasks.adapter = TodoAdapter(todos) {
                dao.deleteTodo(todoItemBiding.todoCheckbox.text.toString())
            }
        }
    }
}