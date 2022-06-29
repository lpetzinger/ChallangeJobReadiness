package com.example.challangereadness.viewHolder

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.R
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.repository.API.Product.ProductEntity
import com.squareup.picasso.Picasso

class ProductViewHolder(private val binding: CardProductBinding, private val listener: ProductListener): RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity) {
        val image = binding.imageProduct
        binding.textTitle.text = product.body?.title
        binding.textPrice.text = product.body?.price.toString()
        binding.cardProduct.setOnClickListener{
            listener.onCLick(product.body!!.title)
        }
        Picasso.get().load(product.body?.secure_thumbnail).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(image)
    }
}