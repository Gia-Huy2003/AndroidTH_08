package com.example.bai85

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TaskManager {
    private const val PREFS_NAME = "task_prefs"
    private const val KEY_TASK_LIST = "task_list"

    // Lấy danh sách tác vụ từ SharedPreferences
    fun getTasks(context: Context): MutableList<Task> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_TASK_LIST, "[]")
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return Gson().fromJson(json, type) ?: mutableListOf()
    }

    // Lưu danh sách tác vụ vào SharedPreferences
    fun saveTasks(context: Context, tasks: MutableList<Task>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(tasks)
        editor.putString(KEY_TASK_LIST, json)
        editor.apply()
    }
}
