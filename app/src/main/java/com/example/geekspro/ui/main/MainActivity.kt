package com.example.geekspro.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekspro.databinding.ActivityMainBinding
import com.example.geekspro.model.Task
import com.example.geekspro.ui.adapter.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TaskAdapter()
        binding.recyclerViewTasks.adapter = adapter
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.allTasks.observe(this, { tasks ->
            adapter.submitList(tasks)
        })

        binding.buttonAddTask.setOnClickListener {
            val taskTitle = binding.editTextTaskTitle.text.toString()
            if (taskTitle.isNotBlank()) {
                val task = Task(title = taskTitle)
                taskViewModel.insert(task)
                binding.editTextTaskTitle.text.clear()
            }
        }
    }
}