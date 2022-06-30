package com.example.challangereadness.infra

import android.icu.text.NumberFormat
import android.icu.util.Currency

class ConstantKeys {
    companion object {
        const val ID = "id"
        const val PRODUCT_INFO = "ProductInfo"
        const val FAVORITES = "Favorites"
        const val ADD_FAVORITES = "Produto adicionado aos Favoritos"
        const val REMOVE_FAVORITES = "Produto removido dos Favoritos"
        const val IMAGE = "image"
        const val TITLE = "title"
        const val PRICE = "price"
         val ALLOW_SEARCH = listOf<String>("Computador", "violaocelo")

        fun formatToCurrency(value: Float, isoCode: String, decimals: Int): String {
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = decimals
            format.currency = Currency.getInstance(isoCode)
            return format.format(value)
        }
    }
}