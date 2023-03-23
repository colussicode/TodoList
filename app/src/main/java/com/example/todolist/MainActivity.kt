package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter(
            onEditTodo = { todoTitle, todoId ->
                editTodo(todoTitle, todoId)
            },
            onRemoveTodo = { todoTitle ->
                removeTodo(todoTitle)
            }
        )
    }

    private lateinit var dao: TodoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        lifecycleScope.launch(Dispatchers.IO) {
            val todos = dao.getTodos()
            withContext(Dispatchers.Main) {
                todoAdapter.updateList(todos)
            }
        }
    }

    private fun removeTodo(title: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao.deleteTodo(title)
            val todos = dao.getTodos()
            withContext(Dispatchers.Main) {
                todoAdapter.updateList(todos)
            }
        }
    }

    private fun editTodo(todoTitle: String, todoId: Int) {
        val editTodoIntent = Intent(this, EditTodoActivity::class.java)
        editTodoIntent.putExtra("todoTitle", todoTitle)
        editTodoIntent.putExtra("todoId", todoId)

        startActivity(editTodoIntent)
    }
}