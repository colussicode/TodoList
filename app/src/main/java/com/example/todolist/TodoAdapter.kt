package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val onRemoveTodo: (todoTitle: String) -> Unit
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoModel> = emptyList()

    fun updateList(todoList: List<TodoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = todoList[position].todoTitle

        holder.checkBox.setOnClickListener {
            onRemoveTodo(todoList[position].todoTitle)
        }
    }

    override fun getItemCount() = todoList.size
}