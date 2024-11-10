package com.example.bai82

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: Switch
    private lateinit var switchFontSize: Switch
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "UserSettings"
    private val KEY_DARK_MODE = "darkMode"
    private val KEY_FONT_SIZE = "fontSize"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchDarkMode = findViewById(R.id.switchDarkMode)
        switchFontSize = findViewById(R.id.switchFontSize)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Lấy cài đặt đã lưu từ SharedPreferences
        val darkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        val fontSizeLarge = sharedPreferences.getBoolean(KEY_FONT_SIZE, false)

        // Áp dụng cài đặt ban đầu cho các Switch
        switchDarkMode.isChecked = darkMode
        switchFontSize.isChecked = fontSizeLarge

        // Cập nhật giao diện theo cài đặt đã lưu
        updateUI(darkMode, fontSizeLarge)

        // Lắng nghe sự thay đổi của các Switch
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
            updateUI(isChecked, switchFontSize.isChecked)
        }

        switchFontSize.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
            updateUI(switchDarkMode.isChecked, isChecked)
        }
    }

    private fun savePreferences() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_DARK_MODE, switchDarkMode.isChecked)
        editor.putBoolean(KEY_FONT_SIZE, switchFontSize.isChecked)
        editor.apply()
    }

    private fun updateUI(darkMode: Boolean, fontSizeLarge: Boolean) {
        // Cập nhật chế độ tối
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Cập nhật kích thước chữ
        val textViewSample: TextView = findViewById(R.id.switchFontSize)
        if (fontSizeLarge) {
            textViewSample.textSize = 24f  // Kích thước chữ lớn
        } else {
            textViewSample.textSize = 14f  // Kích thước chữ nhỏ
        }
    }

    override fun onResume() {
        super.onResume()
        // Đảm bảo giao diện được cập nhật khi activity được mở lại
        val darkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        val fontSizeLarge = sharedPreferences.getBoolean(KEY_FONT_SIZE, false)
        updateUI(darkMode, fontSizeLarge)
    }
}
