package com.example.todolist.view.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.todolist.MainApplication
import com.example.todolist.databinding.FragmentCreateTodoBinding
import com.example.todolist.db.TodoModel
import com.example.todolist.util.hideKeyboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTodoFragment : Fragment() {

    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var createTodoViewModel: CreateTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTodoViewModel = ViewModelProvider(
            this, CreateTodoViewModelFactory(
                (activity?.application as MainApplication).databaseInstance.todoDao()
            )
        )[CreateTodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTodoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCreateTodo.setOnClickListener {
            it.hideKeyboard()
            createTodo()
        }
    }

    private fun createTodo() {
        lifecycleScope.launch(Dispatchers.IO) {
            val model = TodoModel(todoId = 0, todoTitle = binding.edtTextNewTodo.text.toString())
            (activity?.application as MainApplication).databaseInstance.todoDao().createTodo(model)
            createTodoViewModel.createTodo(TodoModel(todoTitle = binding.edtTextNewTodo.text.toString()))
        }
        activity?.onBackPressed()
    }

    companion object {

        val FRAGMENT_NAME = CreateTodoFragment::class.java.simpleName

        fun newInstance() = CreateTodoFragment()
    }
}