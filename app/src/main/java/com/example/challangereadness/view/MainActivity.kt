package com.example.challangereadness.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
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
        setContentView(binding.root)
        setListeners()

    }

    private fun getCategory() {
        viewModel.getCategory(binding)

    }

    private fun setListeners() {
        binding.imageSearch.setOnClickListener {
            getCategory()
        }

        binding.editTextInputSearch.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getCategory()
                return@OnKeyListener true
            }
            false
        })

        val productsState = StatePreferences(this)
        val productListener = object : ProductListener {
            override fun onCLick(image: String?, title: String?, price: String?) {
                productsState.setProductState(ConstantKeys.IMAGE, image)
                productsState.setProductState(ConstantKeys.TITLE, title)
                productsState.setProductState(ConstantKeys.PRICE, price)
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






