package com.example.todolist.view.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolist.MainApplication
import com.example.todolist.databinding.FragmentEditTodoBinding
import com.example.todolist.util.hideKeyboard

class EditTodoFragment : Fragment() {

    private lateinit var binding: FragmentEditTodoBinding
    private val editTodoViewModel: EditTodoViewModel by viewModels() {
        (activity?.application as MainApplication).viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditTodoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTodoTitle()

        binding.buttonEditTodo.setOnClickListener {
            it.hideKeyboard()
            changeTodoName()
        }
    }

    private fun getTodoTitle() {
        binding.edtTextEditTodo.setText(arguments?.getString(KEY_TODO_TITLE))
    }

    private fun changeTodoName() {
        val newTitle = binding.edtTextEditTodo.text.toString()
        val todoId = arguments?.getInt(KEY_TODO_ID) ?: 0

        editTodoViewModel.updateTodo(newTitle, todoId)
        activity?.onBackPressed()
    }

    companion object {

        val FRAGMENT_NAME = EditTodoFragment::class.java.simpleName
        const val KEY_TODO_TITLE = "todoTitle"
        const val KEY_TODO_ID = "todoId"

        fun newInstance(todoTitle: String, todoId: Int): EditTodoFragment {
            return EditTodoFragment().apply {
                this.arguments = Bundle().apply {
                    putString(KEY_TODO_TITLE, todoTitle)
                    putInt(KEY_TODO_ID, todoId)
                }
            }
        }
    }
}