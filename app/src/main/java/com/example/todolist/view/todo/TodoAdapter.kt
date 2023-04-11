package com.example.todolist.view.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding
import com.example.todolist.db.TodoModel

class TodoAdapter(
    private val onEditTodo: (todoTitle: String, todoId: Int) -> Unit,
    private val onRemoveTodo: (todoTitle: String) -> Unit,
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoModel> = emptyList()

    fun updateList(todoList: List<TodoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]
        holder.bind(item, onEditTodo, onRemoveTodo)
    }

    override fun getItemCount() = todoList.size

    class ViewHolder(binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val checkBox = binding.todoCheckbox
        private val todoBox = binding.todoBox

        fun bind(
            item: TodoModel,
            onEditTodo: (todoTitle: String, todoId: Int) -> Unit,
            onRemoveTodo: (todoTitle: String) -> Unit
        ) {
            checkBox.text = item.todoTitle

            if(checkBox.isChecked) checkBox.isChecked = false

            checkBox.setOnClickListener {
                onRemoveTodo(item.todoTitle)
            }
            todoBox.setOnClickListener {
                onEditTodo(item.todoTitle, item.todoId)
            }
        }
    }
}