package com.example.challangereadness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challangereadness.R
import com.example.challangereadness.databinding.ActivityProductDetailsBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        getProductInfo()
        setContentView(binding.root)

    }

    private fun getProductInfo() {
        val productState = StatePreferences(this)
        val image: String = productState.getProductState(ConstantKeys.IMAGE)
        val title: String = productState.getProductState(ConstantKeys.TITLE)
        val price: String = productState.getProductState(ConstantKeys.PRICE)
        binding.textTitle.text = title
        binding.textPrice.text = price
        Picasso.get().load(image).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.imageProduct)
    }
}