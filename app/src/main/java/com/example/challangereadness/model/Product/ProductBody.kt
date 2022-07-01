package com.example.challangereadness.model.Product

import com.google.gson.annotations.SerializedName

class ProductBody {
    val id: String = ""
    var title: String = ""
    var price: Float = 0F
    var pictures: List<Pictures> = listOf()

    @SerializedName("original_price")
    var originalPrice: Float = 0F
    var condition: String = ""

    @SerializedName("sold_quantity")
    var soldQuantity: Int = 0

}