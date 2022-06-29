package com.example.challangereadness.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.repository.API.Category.CategoryEntity
import com.example.challangereadness.repository.API.Category.CategoryService
import com.example.challangereadness.repository.API.Product.ProductEntity
import com.example.challangereadness.repository.API.Product.ProductsRepository
import com.example.challangereadness.repository.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _products = MutableLiveData<List<ProductEntity>>()
    private val _category = MutableLiveData<String>()

    val products: LiveData<List<ProductEntity>>
        get() = _products

    val category: LiveData<String>
        get() = _category

    private val productsRepository = ProductsRepository.getInstance(application)


    fun getCategory(binding: ActivityMainBinding) {
        val service = RetrofitClient.create(CategoryService::class.java)
        val call: Call<List<CategoryEntity>> =
            service.getCategory(1, binding.editTextInputSearch.text.toString())
        call.enqueue(object : Callback<List<CategoryEntity>> {
            override fun onResponse(
                call: Call<List<CategoryEntity>>, response: Response<List<CategoryEntity>>
            ) {
                response.body()?.get(0)?.let { setCategory(it.categoryId) }

            }

            override fun onFailure(call: Call<List<CategoryEntity>>, t: Throwable) {
                Log.d("xablau", "$t")
            }

        })
    }

    fun setCategory(category: String) {
        _category.value = category
        productsRepository.setCategory(category, _products)
    }


}

