package com.example.bai84

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)

        // Kiểm tra trạng thái đăng nhập
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            // Nếu đã đăng nhập, chuyển đến màn hình chính
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // Đóng màn hình đăng nhập
        }

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if (username == "giahuy" && password == "1234") {
                // Lưu trạng thái đăng nhập vào SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("username", username)
                editor.apply()

                // Chuyển đến màn hình chính
                startActivity(Intent(this, MainActivity::class.java))
                finish()  // Đóng màn hình đăng nhập
            } else {
                edtUsername.error = "Tên người dùng hoặc mật khẩu không đúng!"
            }
        }
    }
}
