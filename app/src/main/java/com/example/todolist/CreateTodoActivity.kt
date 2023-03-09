package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityCreateTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTodoBinding
    private lateinit var dao: TodoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDatabase.getInstance(applicationContext).todoDao()
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateTodo.setOnClickListener {
            createTodo()
        }
    }

    private fun createTodo() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            dao.createTodo(
                TodoModel(
                    binding.edtTextNewTodo.text.toString()
                )
            )
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}