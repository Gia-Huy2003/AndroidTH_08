package com.example.bai84

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogout: Button
    private lateinit var txtWelcome: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtWelcome = findViewById(R.id.txtWelcome)
        btnLogout = findViewById(R.id.btnLogout)

        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)

        // Hiển thị lời chào với tên người dùng
        txtWelcome.text = "Chào mừng, ${sharedPreferences.getString("username", "")}"

        // Đăng xuất
        btnLogout.setOnClickListener {
            // Xóa trạng thái đăng nhập
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            // Quay lại màn hình đăng nhập
            startActivity(Intent(this, LoginActivity::class.java))
            finish()  // Đóng màn hình chính
        }
    }
}
