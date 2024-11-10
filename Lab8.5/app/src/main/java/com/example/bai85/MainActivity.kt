package com.example.bai85

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tasks: MutableList<Task>
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var etTaskName: EditText
    private lateinit var btnAddTask: Button
    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        etTaskName = findViewById(R.id.etTaskName)
        btnAddTask = findViewById(R.id.btnAddTask)
        rvTasks = findViewById(R.id.rvTasks)

        // Lấy danh sách tác vụ từ SharedPreferences
        tasks = TaskManager.getTasks(this)

        // Khởi tạo RecyclerView với adapter
        taskAdapter = TaskAdapter(tasks) { task ->
            // Logic cho việc chỉnh sửa tác vụ nếu cần thiết (ví dụ: sửa tên tác vụ)
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter

        // Khi nhấn nút thêm tác vụ
        btnAddTask.setOnClickListener {
            val taskName = etTaskName.text.toString()
            if (taskName.isNotBlank()) {
                val newTask = Task(id = tasks.size + 1, name = taskName)
                tasks.add(newTask)
                TaskManager.saveTasks(this, tasks)
                taskAdapter.notifyDataSetChanged()
                etTaskName.text.clear()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // Lưu danh sách tác vụ khi thoát ứng dụng
        TaskManager.saveTasks(this, tasks)
    }
}