package com.example.challangereadness.viewHolder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.challangereadness.R
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.model.Product.ProductModel
import com.example.challangereadness.view.MainActivity
import com.squareup.picasso.Picasso

class ProductViewHolder(
    private val binding: CardProductBinding,
    private val listener: ProductListener,
    val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductModel) {
        val productImage = product.body!!.pictures[0].secure_url
        val productTitle = product.body!!.title
        val productPrice = ConstantKeys.formatToCurrency(product.body!!.price, "BRL", 2)
        binding.textInstallment.text = binding.textInstallment.text.toString().replace(
            "R$00",
            ConstantKeys.formatToCurrency(product.body!!.price / 18, "BRL", 2)
        )

        binding.textTitle.text = productTitle
        binding.textPrice.text = productPrice
        binding.cardProduct.setOnClickListener {
            listener.onCLick(product.body!!.id)

        }
        binding.imageEllipseFavorite.setOnClickListener {
            listener.onFavorite(binding, product.body!!.id)


        }
        Picasso.get().load(productImage).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.imageProduct)


        val favorites = StatePreferences(context).getFavoriteState(ConstantKeys.FAVORITES)
        val isFavorite = favorites.contains(product.body!!.id)
        val source = if (isFavorite) R.drawable.ic_is_favorite else R.drawable.ic_favorite
        Picasso.get().load(source).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.buttonFavorite)


    }


}