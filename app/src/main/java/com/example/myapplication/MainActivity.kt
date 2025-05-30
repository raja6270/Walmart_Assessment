package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.CountryAdapter
import com.example.myapplication.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        viewModel.countries.observe(this) { result ->
            binding.progressBar.visibility = View.GONE
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    adapter.submitList(it)
                }
            } else {
                Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadCountries()
    }
}