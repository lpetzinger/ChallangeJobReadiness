package com.example.challangereadness.repository.API.Product

import com.example.challangereadness.repository.API.HighLight.HighLights
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("highlights/MLB/category/{category}")
    fun getIdProducts(@Path("category") _category: String): Call<HighLights>

    @GET("items")
    fun getProducts(@Query("ids") newQueryString: String): Call<List<ProductEntity>>

}