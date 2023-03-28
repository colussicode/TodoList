package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.TodoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dao: TodoDAO by lazy {
        (application as MainApplication).databaseInstance.todoDao()
    }

    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter(onEditTodo = { todoTitle, todoId ->
            editTodo(todoTitle, todoId)
        }, onRemoveTodo = { todoTitle ->
            removeTodo(todoTitle)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        startActivity(Intent(this, CreateTodoActivity::class.java))
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
        startActivity(Intent(this, EditTodoActivity::class.java).apply {
            putExtra("todoTitle", todoTitle)
            putExtra("todoId", todoId)
        })
    }
}