package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val onEditTodo: (todoTitle: String, todoId: Int) -> Unit,
    private val onRemoveTodo: (todoTitle: String) -> Unit,
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoModel> = emptyList()

    fun updateList(todoList: List<TodoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
        val todoBox: RelativeLayout = view.findViewById(R.id.todo_box)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]

        holder.checkBox.text = item.todoTitle
        if(holder.checkBox.isChecked) {
            holder.checkBox.isChecked = false
        }
        holder.checkBox.setOnClickListener {
            onRemoveTodo(item.todoTitle)
        }
        holder.todoBox.setOnClickListener {
            onEditTodo(item.todoTitle, item.todoId)
        }
    }

    override fun getItemCount() = todoList.size
}