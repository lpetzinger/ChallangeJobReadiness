package com.example.challangereadness.service

import com.example.challangereadness.model.Category.CategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET("sites/MLB/domain_discovery/search")
    fun getCategory(
        @Query("limit") qtd: Int,
        @Query("q") term: String = "computador"
    ): Call<List<CategoryModel>>
}