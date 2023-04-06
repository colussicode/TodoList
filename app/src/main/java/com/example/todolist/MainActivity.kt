package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.TodoDAO
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dao: TodoDAO by lazy {
        (application as MainApplication).databaseInstance.todoDao()
    }

    private val todoAdapter = TodoAdapter(
        ::editTodo,
        ::removeTodo
    )

    private lateinit var mainViewModel: MainViewModel

    val createTodoFragment = CreateTodoFragment()
    val editTodoFragment = EditTodoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(dao))[MainViewModel::class.java]

        initRv()
        observeTodoLiveData()
        getTodos()

        binding.buttonNewTodo.setOnClickListener {
            goToCreateTodoScreen()
        }
    }


    override fun onResume() {
        super.onResume()
        getTodos()
    }

    private fun observeTodoLiveData() {
        mainViewModel.todos.observe(this){ todos ->
            todoAdapter.updateList(todos)
        }
    }

    private fun goToCreateTodoScreen() {
        startActivity(Intent(this, CreateOrEditTodoActivity::class.java))

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, createTodoFragment)
            commit()
        }
    }

    private fun initRv() {
        binding.rvTasks.layoutManager = LinearLayoutManager(baseContext)
        binding.rvTasks.adapter = todoAdapter
    }

    private fun getTodos() {
        mainViewModel.getTodos()
    }

    private fun removeTodo(title: String) {
        mainViewModel.deleteTodo(title)
    }

    private fun editTodo(todoTitle: String, todoId: Int) {

        startActivity(Intent(this, CreateOrEditTodoActivity::class.java).apply {
            putExtra("todoTitle", todoTitle)
            putExtra("todoId", todoId)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, editTodoFragment)
                commit()
            }
        })
    }
}
