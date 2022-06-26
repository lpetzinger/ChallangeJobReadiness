package com.example.challangereadness.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.model.ProductModel

class ProductViewHolder(private val binding: CardProductBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductModel) {
        binding.textTitle.text = product.title
        binding.textPrice.text = product.price.toString()
    }
}