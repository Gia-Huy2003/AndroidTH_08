package com.example.bai83

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "AppPrefs"
        private const val KEY_LAUNCH_COUNT = "launch_count"
    }

    private lateinit var tvLaunchCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lấy đối tượng TextView từ layout
        tvLaunchCount = findViewById(R.id.tvLaunchCount)

        // Lấy SharedPreferences để đọc dữ liệu số lần mở ứng dụng
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Lấy số lần mở ứng dụng hiện tại, mặc định là 0 nếu chưa có
        var launchCount = sharedPreferences.getInt(KEY_LAUNCH_COUNT, 0)

        // Tăng số lần mở ứng dụng lên 1
        launchCount++

        // Lưu lại số lần mở ứng dụng mới vào SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_LAUNCH_COUNT, launchCount)
        editor.apply()

        // Cập nhật TextView với số lần mở ứng dụng mới
        tvLaunchCount.text = "Số lần mở ứng dụng: $launchCount"
    }
}