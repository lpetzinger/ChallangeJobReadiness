package com.example.challangereadness.repository.API.Category

import com.example.challangereadness.repository.API.Category.CategoryEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryService {
    @GET("sites/MLB/domain_discovery/search")
    fun getCategory(
        @Query("limit") qtd: Int,
        @Query("q") term: String = "computador"
    ): Call<List<CategoryEntity>>
}