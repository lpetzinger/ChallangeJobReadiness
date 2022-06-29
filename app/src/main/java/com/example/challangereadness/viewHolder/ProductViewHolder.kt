package com.example.challangereadness.viewHolder

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.R
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.infra.StatePreferences
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.repository.API.Product.ProductEntity
import com.squareup.picasso.Picasso

class ProductViewHolder(private val binding: CardProductBinding, private val listener: ProductListener): RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity) {
        val productImage = product.body!!.pictures[0].secure_url
        val productTitle = product.body!!.title
        val productPrice = product.body!!.price.toString()


        binding.textTitle.text = productTitle
        binding.textPrice.text = productPrice
        binding.cardProduct.setOnClickListener{
            listener.onCLick(productImage,productTitle, productPrice)

        }
        Picasso.get().load(productImage).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.imageProduct)
    }
}