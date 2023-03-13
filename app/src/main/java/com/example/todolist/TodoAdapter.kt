package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

class TodoAdapter(
    private val onRemoveTodo: (todoTitle: String) -> Unit,
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoModel> = emptyList()

    fun updateList(todoList: List<TodoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    class ViewHolder(binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        var itemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]
        holder.itemBinding.todoCheckbox.text = item.todoTitle
        holder.itemBinding.todoCheckbox.setOnClickListener {
            onRemoveTodo(item.todoTitle)
            holder.itemBinding.todoCheckbox.isChecked = false
        }
    }

    override fun getItemCount() = todoList.size
}