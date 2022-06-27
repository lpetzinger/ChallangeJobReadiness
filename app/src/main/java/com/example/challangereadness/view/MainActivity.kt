package com.example.challangereadness.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.CategoryListener
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = ProductsAdapter()
    private val categories: MutableList<String> = ArrayList()
    private val supportDropDown = androidx.appcompat.R.layout.support_simple_spinner_dropdown_item

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        startAdapter()
        getCategories()
        getAll()
        observe()
        setContentView(binding.root)
    }


    private fun getCategories() {

        for (i in 1..30) {
            categories.add("category $i")
        }
        val dropdown = binding.spinnerCategories
        dropdown.adapter = ArrayAdapter(this, supportDropDown, categories)
        dropdown.onItemSelectedListener = CategoryListener(this, categories, viewModel)
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
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProducts.adapter = adapter
    }
}






