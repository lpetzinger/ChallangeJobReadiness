package com.example.challangereadness.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.repository.API.Category.CategoryEntity
import com.example.challangereadness.repository.API.Category.CategoryService
import com.example.challangereadness.repository.API.RetrofitClient
import com.example.challangereadness.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding.imageSearch.setOnClickListener {
            getCategory()
        }
        getCategory()
        startAdapter()
        observe()
        setContentView(binding.root)

    }

    private fun getCategory() {
        viewModel.getCategory(binding)

    }

    private fun observe() {
        viewModel.products.observe(this) {
            adapter.updateProducts(it)

        }
    }


    private fun startAdapter() {
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProducts.adapter = adapter
    }
}






