package com.example.challangereadness.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.model.Category.CategoryModel
import com.example.challangereadness.service.CategoryService
import com.example.challangereadness.model.Product.ProductModel
import com.example.challangereadness.repository.ProductsRepository
import com.example.challangereadness.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _products = MutableLiveData<List<ProductModel>>()
    private val _category = MutableLiveData<String>()

    val products: LiveData<List<ProductModel>>
        get() = _products

    val category: LiveData<String>
        get() = _category

    private val productsRepository = ProductsRepository.getInstance(application)


    fun getCategory(binding: ActivityMainBinding) {
        val service = RetrofitClient.create(CategoryService::class.java)
        val call: Call<List<CategoryModel>> =
            service.getCategory(1, binding.editTextInputSearch.text.toString())
        call.enqueue(object : Callback<List<CategoryModel>> {
            override fun onResponse(
                call: Call<List<CategoryModel>>, response: Response<List<CategoryModel>>
            ) {
                if (response.raw().code() != 200) {
                    setCategory("MLB437616")
                } else {
                    response.body()?.get(0)?.let { setCategory(it.categoryId) }
                }

            }

            override fun onFailure(call: Call<List<CategoryModel>>, t: Throwable) {
                Log.d("xablau", "$t")
            }

        })
    }

    fun setCategory(category: String) {
        _category.value = category
        productsRepository.setCategory(category, _products)
    }


}

