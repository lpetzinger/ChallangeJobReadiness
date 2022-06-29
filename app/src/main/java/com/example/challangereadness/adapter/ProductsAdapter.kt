package com.example.challangereadness.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.repository.API.Product.ProductEntity
import com.example.challangereadness.viewHolder.ProductViewHolder


class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private var productsList: List<ProductEntity> = listOf()
    private lateinit var listener: ProductListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val cardProduct =
            CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(cardProduct, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.count()


    fun updateProducts(list: List<ProductEntity>) {
        productsList = list
        notifyDataSetChanged()

    }

    fun attachListener(productListener: ProductListener){
        listener = productListener
    }

}