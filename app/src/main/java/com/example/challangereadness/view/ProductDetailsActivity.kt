package com.example.challangereadness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.challangereadness.R
import com.example.challangereadness.databinding.ActivityProductDetailsBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
import com.example.challangereadness.viewModel.MainViewModel
import com.example.challangereadness.viewModel.ProductDetailsViewModel
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]


        super.onCreate(savedInstanceState)
        getProductInfo()
        setListeners()
        setContentView(binding.root)

    }

    private fun setListeners() {
       binding.imageButtonBack.setOnClickListener{
           finish()
       }
    }

    private fun getProductInfo() {
        val productState = StatePreferences(this)
        val id: String = productState.getProductState(ConstantKeys.ID)

        viewModel.getProductInfo(binding, id)
    }
}