package com.example.challangereadness.service

import com.example.challangereadness.model.HighLight.HighLightsModel
import com.example.challangereadness.model.Product.Description
import com.example.challangereadness.model.Product.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("highlights/MLB/category/{category}")
    fun getIdProducts(@Path("category") _category: String): Call<HighLightsModel>

    @GET("items")
    fun getProducts(@Query("ids") newQueryString: String): Call<List<ProductModel>>

    @GET("items/{itemId}/description")
    fun getDescription(@Path("itemId") itemId: String): Call<Description>

}