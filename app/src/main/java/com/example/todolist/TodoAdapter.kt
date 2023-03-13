package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
    }

    //TODO: Can't use ViewBinding here (Loosing style properties)
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
    }

    override fun getItemCount() = todoList.size
}