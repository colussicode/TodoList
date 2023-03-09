package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

class TodoAdapter(

    private val context: Context,
    private val todoList: List<TodoModel>,
    private val onRemoveTodo: (todoTitle: String) -> Unit

): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    class ViewHolder(binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        val checkBox: CheckBox = binding.todoCheckbox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TodoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = todoList[position].todoTitle

        holder.checkBox.setOnClickListener {
            onRemoveTodo(todoList[position].todoTitle)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = todoList.size
}