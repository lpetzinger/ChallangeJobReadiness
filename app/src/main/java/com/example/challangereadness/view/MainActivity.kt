package com.example.challangereadness.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        getCategory()
        startAdapter()
        observe()
        setListeners()
        setContentView(binding.root)

    }

    private fun getCategory() {
        viewModel.getCategory(binding)

    }

    private fun setListeners() {
        binding.imageSearch.setOnClickListener {
            getCategory()
        }

        binding.editTextInputSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getCategory()
                return@OnKeyListener true
            }
            false
        })

        val productListener = object : ProductListener{
            override fun onCLick(productTitle: String) {
               startActivity(Intent(this@MainActivity, ProductDetailsActivity::class.java))
            }

        }

        adapter.attachListener(productListener)

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






