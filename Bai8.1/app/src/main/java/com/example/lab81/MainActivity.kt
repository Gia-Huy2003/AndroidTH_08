package com.example.lab81

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var btnRetrieve: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        editText = findViewById(R.id.editText)
        btnSave = findViewById(R.id.btnSave)
        btnRetrieve = findViewById(R.id.btnRetrieve)

        // Lưu dữ liệu khi nhấn nút "Lưu Dữ Liệu"
        btnSave.setOnClickListener {
            val dataToSave = editText.text.toString()
            if (dataToSave.isNotEmpty()) {
                // Lưu vào SharedPreferences
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("savedData", dataToSave)
                editor.apply()  // Hoặc commit()
                Toast.makeText(this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show()
            }
        }

        // Lấy dữ liệu khi nhấn nút "Lấy Dữ Liệu"
        btnRetrieve.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val savedData = sharedPreferences.getString("savedData", null)
            if (savedData != null) {
                // Nếu có dữ liệu lưu, hiển thị trong Toast
                Toast.makeText(this, "Dữ liệu lưu: $savedData", Toast.LENGTH_SHORT).show()
            } else {
                // Nếu không có dữ liệu
                Toast.makeText(this, "Không có dữ liệu lưu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}