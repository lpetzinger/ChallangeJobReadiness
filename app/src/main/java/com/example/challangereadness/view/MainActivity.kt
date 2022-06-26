package com.example.challangereadness.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        startAdapter()
        getAll()
        observe()
        setContentView(binding.root)
    }

    private fun observe() {
        viewModel.products.observe(this) {
            adapter.updateProducts(it)
        }
    }


    private fun getAll() {
        viewModel.getAll()
    }

    private fun startAdapter() {
        binding.recyclerProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProducts.adapter = adapter
    }
}






