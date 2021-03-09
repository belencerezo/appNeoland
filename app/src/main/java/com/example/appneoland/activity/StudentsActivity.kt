package com.example.appneoland.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appneoland.adapter.StudentsAdapter
import com.example.appneoland.databinding.ActivityStudentsBinding
import com.example.appneoland.viewModel.StudentsViewModel
import kotlinx.coroutines.launch

class StudentsActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentsBinding

    private var adapter = StudentsAdapter()

    private lateinit var model: StudentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(StudentsViewModel::class.java)
        createRecycler()

        lifecycleScope.launch {
            model.listStudents.observe(this@StudentsActivity) {
                adapter.updateData(it)
            }
        }
        binding.addNewUser.setOnClickListener {
            startAddUser()
        }
    }

    override fun onResume() {
        super.onResume()
        model.getAllStudents()
    }

    private fun createRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@StudentsActivity)
        binding.recyclerView.adapter = adapter
    }

    private fun startAddUser(){
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }

}