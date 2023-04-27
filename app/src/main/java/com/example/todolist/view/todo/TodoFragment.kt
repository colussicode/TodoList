package com.example.todolist.view.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.MainApplication
import com.example.todolist.R
import com.example.todolist.databinding.FragmentTodoBinding
import com.example.todolist.view.create.CreateTodoFragment
import com.example.todolist.view.edit.EditTodoFragment

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    private val todoViewModel: TodoViewModel by viewModels() {
        (activity?.application as MainApplication).viewModelFactory
    }

    private val todoAdapter = TodoAdapter(
        onEditTodo = ::editTodo,
        onRemoveTodo = ::removeTodo
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater)

        setupAdapter()

        todoViewModel.todos.observe(viewLifecycleOwner) { todos ->
            todoAdapter.updateList(todos)
            if(todos.isEmpty()) binding.ctnContent.visibility = View.VISIBLE else binding.ctnContent.visibility = View.GONE
        }

        binding.buttonNewTodo.setOnClickListener {
            goToCreateTodoScreen()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getTodos()
    }

    private fun goToCreateTodoScreen() {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.main_container, CreateTodoFragment.newInstance()
        )?.addToBackStack(CreateTodoFragment.FRAGMENT_NAME)?.commit()
    }

    private fun setupAdapter() {
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
    }

    private fun getTodos() {
        todoViewModel.getTodos()
    }

    private fun removeTodo(title: String) {
        todoViewModel.deleteTodo(title)
    }

    private fun editTodo(todoTitle: String, todoId: Int) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.main_container, EditTodoFragment.newInstance(todoTitle, todoId)
        )?.addToBackStack(EditTodoFragment.FRAGMENT_NAME)?.commit()
    }

    companion object {

        fun newInstance() = TodoFragment()
    }
}