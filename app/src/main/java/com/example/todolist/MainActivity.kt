package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: TodoDAO
    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter { todoTitle ->
            removeTodo(todoTitle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = AppDatabase.getInstance(baseContext).todoDao()

        initRv()
        getTodos()

        binding.buttonNewTodo.setOnClickListener {
            goToCreateTodoScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        getTodos()
    }

    private fun goToCreateTodoScreen() {
        val intent =  Intent(this, CreateTodoActivity::class.java)
        startActivity(intent)
    }

    private fun initRv() {
        binding.rvTasks.layoutManager = LinearLayoutManager(baseContext)
        binding.rvTasks.adapter = todoAdapter
    }

    private fun getTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            val todos = dao.getTodos()
            withContext(Dispatchers.Main) {
                todoAdapter.updateList(todos)
            }
        }
    }

    private fun removeTodo(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteTodo(title)
            val todos = dao.getTodos()
            withContext(Dispatchers.Main) {
                todoAdapter.updateList(todos)
            }
        }
    }
}