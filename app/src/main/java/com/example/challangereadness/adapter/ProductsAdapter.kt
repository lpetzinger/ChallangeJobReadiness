package com.example.challangereadness.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.model.ProductModel
import com.example.challangereadness.viewHolder.ProductViewHolder


class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private var productsList: List<ProductModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val cardProduct =
            CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(cardProduct)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.count()


    fun updateProducts(list: List<ProductModel>) {
        productsList = list

    }

}