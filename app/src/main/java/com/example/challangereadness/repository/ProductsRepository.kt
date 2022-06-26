package com.example.challangereadness.repository

import android.content.Context
import com.example.challangereadness.model.ProductModel

class ProductsRepository private constructor(context: Context){

    companion object {
        private lateinit var repository: ProductsRepository

        fun getInstance(context: Context): ProductsRepository {
            if (!Companion::repository.isInitialized) {
                repository = ProductsRepository(context)
                return repository
            }
            return repository
        }
    }

    fun getAll(): MutableList<ProductModel> {
        val list = mutableListOf<ProductModel>()
        list.add(ProductModel("Product1", "src", 100F))
        list.add(ProductModel("Product2", "src", 100F))
        list.add(ProductModel("Product2", "src", 500F))

        return list
    }

}