package com.example.todolist.view.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolist.MainApplication
import com.example.todolist.databinding.FragmentCreateTodoBinding
import com.example.todolist.db.TodoModel
import com.example.todolist.util.hideKeyboard

class CreateTodoFragment : Fragment() {

    private lateinit var binding: FragmentCreateTodoBinding
    private val createTodoViewModel: CreateTodoViewModel by viewModels() {
        (activity?.application as MainApplication).viewModelFactory
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
        createTodoViewModel.createTodo(
            TodoModel(todoTitle = binding.edtTextNewTodo.text.toString())
        )
        activity?.onBackPressed()
    }

    companion object {

        val FRAGMENT_NAME = CreateTodoFragment::class.java.simpleName

        fun newInstance() = CreateTodoFragment()
    }
}