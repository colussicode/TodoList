package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

class TodoAdapter(
    private val onEditTodo: (todoTitle: String, todoId: Int) -> Unit,
    private val onRemoveTodo: (todoTitle: String) -> Unit,
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoModel> = emptyList()

    fun updateList(todoList: List<TodoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    class ViewHolder(binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        val checkBox = binding.todoCheckbox
        val todoBox = binding.todoBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]

        holder.checkBox.text = item.todoTitle

        if(holder.checkBox.isChecked) holder.checkBox.isChecked = false

        holder.checkBox.setOnClickListener {
            onRemoveTodo(item.todoTitle)
        }
        holder.todoBox.setOnClickListener {
            onEditTodo(item.todoTitle, item.todoId)
        }
    }

    override fun getItemCount() = todoList.size
}