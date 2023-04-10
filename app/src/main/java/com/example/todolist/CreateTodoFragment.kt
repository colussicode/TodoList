package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentCreateTodoBinding
import com.example.todolist.db.TodoDAO
import com.example.todolist.db.TodoModel

class CreateTodoFragment : Fragment() {
    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var createTodoViewModel: CreateTodoViewModel
    private val dao : TodoDAO by lazy {
        (activity as MainApplication).databaseInstance.todoDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTodoViewModel = ViewModelProvider(this, CreateTodoViewModelFactory(dao))[CreateTodoViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCreateTodo.setOnClickListener { createTodo() }
    }

    private fun createTodo() {
        createTodoViewModel.createTodo(
            TodoModel(
                todoTitle = binding.edtTextEditTodo.text.toString()
            )
        )
    }
}