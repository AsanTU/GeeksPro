package com.example.geekspro.repository

import androidx.lifecycle.LiveData
import com.example.geekspro.database.TaskDao
import com.example.geekspro.model.Task


class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}