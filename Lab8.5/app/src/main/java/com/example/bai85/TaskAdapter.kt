package com.example.bai85

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onTaskClicked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.tvTaskName)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(task: Task) {
            taskName.text = task.name
            itemView.setOnClickListener { onTaskClicked(task) }
            btnDelete.setOnClickListener {
                tasks.remove(task)
                notifyDataSetChanged()
                // Lưu lại danh sách sau khi xóa tác vụ
                TaskManager.saveTasks(itemView.context, tasks)
            }
        }
    }
}